package views

import Constants
import Mutables
import analyzers.LexerAnalyzer
import files.FileHandler
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.paint.Color
import models.Register
import tornadofx.*
import kotlin.concurrent.thread

class Interface: View() {
    override val root = borderpane {
        left<EditorView>()
        right<SymbolsTable>()
        bottom<ErrorsView>()
    }
}

class EditorView : View() {
    private val controller: MyController by inject()
    private val errorView: ErrorsView by inject()
    private val tableView: SymbolsTable by inject()
    private val input = SimpleStringProperty()

    override val root = form {

        style{ setMinHeight(350.0) }

        fieldset {
            button("Compilar") {
                action {
                    progressbar {
                        thread {
                            for (i in 1..100) {
                                Platform.runLater { progress = i.toDouble() / 100.0 }
                                Thread.sleep(10)
                            }

                            Constants.file.delete()
                            controller.insertIntoFile(input.value.split("\n"))

                            var outputStr = ""
                            runAsync {
                                Mutables.errorList.forEach { item ->
                                    outputStr += item.init+" "+item.errorType+", '"+item.token+"' "+item.description+", line "+item.line+"\n"
                                }
                            } ui {
                                errorView.output.value = outputStr
                                println("ERROR_LIST_SIZE: "+Mutables.errorList.size)
                                Mutables.errorList.clear()
                            }

                            runAsync {
                                Mutables.registerList.clear()
                                controller.readFromFile()
                            } ui {
                                val registerList = Mutables.registerList.observable()
                                tableView.registerList.asyncItems { registerList }
                            }
                        }
                    }
                }
            }

            label("Editor") {
                textFill = Color.BLUE
            }
            field {
                textarea(input).minHeight = 350.0
                //textarea(input).text = "& er4 sds // %$ Int class main { fun test ( ) : Int { Int value = 5 + 3 ; Float value = 5.5 ; if ( value < value2 ) { value = 20 ; } return value } }"
            }
        }

    }
}

class MyController: Controller() {
    fun insertIntoFile(inputValue: List<String>){
        val lexerAnalyzer = LexerAnalyzer()
        lexerAnalyzer.analyzeLine(inputValue)
    }
    fun readFromFile(){
        val fileHandler = FileHandler()
        fileHandler.read()
    }
}

class ErrorsView: View() {
    val output = SimpleStringProperty()

    override val root = form {

        fieldset{
            label("Errores") {
                textFill = Color.RED
            }
            field {
                textarea(output)
            }
        }
    }
}

class SymbolsTable: View() {
    val registerList = FXCollections.observableArrayList<Register>()!!
    override val root = form {

        tableview(registerList) {
            readonlyColumn("Token",Register::token).minWidth = 300.0
            readonlyColumn("Type",Register::type)
            readonlyColumn("Size",Register::size)
            readonlyColumn("Value",Register::value)
            readonlyColumn("Category",Register::category)
            readonlyColumn("Position",Register::position)
            readonlyColumn("Typing",Register::typing)
        }
    }
}