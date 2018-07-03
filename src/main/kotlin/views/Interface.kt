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
        fieldset {
            val editorLabel = label("Editor") {
                textFill = Color.BLUE
            }
            val editor = field {
                textarea(input).text = "& er4 sds %#$ Int"
            }

            val errorLabel = label("Errores") {
                textFill = Color.RED
            }
            val error = field {
                textarea(output)
            }

            button("Compilar") {
                action {
                    progressbar {
                        thread {
                            for (i in 1..100) {
                                Platform.runLater { progress = i.toDouble() / 100.0 }
                                Thread.sleep(10)
                            }
                            controller.insertIntoFile(input.value)

                            var outputStr = ""
                            runAsync {
                                App.errorList.forEach { item ->
                                    outputStr += item.init+" "+item.errorType+", token "+item.token+" "+item.description+", line "+item.line+"\n"
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
                    find<MyFragment>().openModal(stageStyle = StageStyle.UTILITY)
                    controller.readFromFile()
                }
            }

        }

    }
}

class MyController: Controller() {
    fun insertIntoFile(inputValue: String){
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
        val register = listOf(
                Register("Int","Int",4,"3","PR",0),
                Register("x","Int",4,"","ID",0)
        ).observable()

        tableview(register) {
            readonlyColumn("Token",Register::token)
            readonlyColumn("Type",Register::type)
            readonlyColumn("Size",Register::size)
            readonlyColumn("Value",Register::value)
            readonlyColumn("Category",Register::category)
            readonlyColumn("Position",Register::position)
        }
    }
}