package views

import analyzers.LexerAnalyzer
import files.FileHandler
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.scene.paint.Color
import javafx.stage.StageStyle
import models.Register
import tornadofx.*
import App
import kotlin.concurrent.thread

class Interface : View() {
    private val controller: MyController by inject()
    private val input = SimpleStringProperty()
    private val output = SimpleStringProperty()

    override val root = form {

        style { setMinSize(900.0,420.0) }

        fieldset {
            val editorLabel = label("Editor") {
                textFill = Color.BLUE
            }
            val editor = field {
                textarea(input).minHeight = 300.0
                //textarea(input).text = "& er4 sds // %$ Int class main { fun test ( ) : Int { Int value = 5 + 3 ; Float value = 5.5 ; if ( value < value2 ) { value = 20 ; } return value } }"
                minHeight = 320.0
            }

            val errorLabel = label("Errores") {
                textFill = Color.RED
            }
            val error = field {
                textarea(output).maxHeight = 100.0
            }

            button("Compilar") {
                action {
                    progressbar {
                        thread {
                            for (i in 1..100) {
                                Platform.runLater { progress = i.toDouble() / 100.0 }
                                Thread.sleep(10)
                            }
                            controller.insertIntoFile(input.value.split("\n"))
                            //val data = input.value.split("\n")


                            var outputStr = ""
                            runAsync {
                                App.errorList.forEach { item ->
                                    outputStr += item.init+" "+item.errorType+", '"+item.token+"' "+item.description+", line "+item.line+"\n"
                                }
                            } ui {
                                output.value = outputStr
                                println("ERROR_LIST_SIZE: "+App.errorList.size)
                                App.errorList.clear()
                            }


                        }
                    }
                }
            }

            button("Tabla de simbolos") {
                action {
                    App.registerList.clear()
                    controller.readFromFile()
                    find<MyFragment>().openModal(stageStyle = StageStyle.UTILITY)
                }
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

class MyFragment: Fragment() {
    override val root = form {

        style { setMinSize(600.0,420.0) }

        val registerList = App.registerList.observable()

        tableview(registerList) {
            readonlyColumn("Token",Register::token)
            readonlyColumn("Type",Register::type)
            readonlyColumn("Size",Register::size)
            readonlyColumn("Value",Register::value)
            readonlyColumn("Category",Register::category)
            readonlyColumn("Position",Register::position)
        }
    }
}