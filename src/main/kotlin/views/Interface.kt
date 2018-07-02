package views

import analyzers.LexerAnalyzer
import files.FileHandler
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.SelectionMode
import javafx.scene.paint.Color
import javafx.stage.StageStyle
import models.Register
import tornadofx.*
import java.time.LocalDate
import java.time.Period
import java.util.ArrayList
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
                textarea(input).text = "& er4 sds Int"
                textarea(output)
            }

            val errorLabel = label("Errores") {
                textFill = Color.RED
            }
            /*val error = field {
                textarea(output)
            }*/

            button("Compilar") {
                action {
                    progressbar {
                        thread {
                            for (i in 1..100) {
                                Platform.runLater { progress = i.toDouble() / 100.0 }
                                Thread.sleep(10)
                            }
                            controller.insertIntoFile(input.value)
                            //controller.readFromFile()
                            val test = Test()
                            output.value = test.getData()
                        }
                    }
                    //controller.writeToDb(output.value)
                    //output.value = ""
                    //controller.insertIntoFile(input.value)
                    //controller.readFromFile()
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
    fun writeToDb(inputValue: String) {
        println("Writing $inputValue to database!")
    }
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
        class Person(val id: Int, val name: String, val birthday: LocalDate) {
            val age: Int get() = Period.between(birthday, LocalDate.now()).years
        }

        val persons = listOf(
                Person(1,"Samantha Stuart",LocalDate.of(1981,12,4)),
                Person(2,"Tom Marks",LocalDate.of(2001,1,23)),
                Person(3,"Stuart Gills",LocalDate.of(1989,5,23)),
                Person(3,"Nicole Williams",LocalDate.of(1998,8,11))
        ).observable()

        tableview(persons) {
            column("ID",Person::id)
            column("Name", Person::name)
            column("Birthday", Person::birthday)
            column("Age",Person::age)
        }
    }
}

class Test(){
    private var data: String = ""

    fun getData(): String {
        return data
    }

    fun setData(data: String) {
        this.data = data
    }
}

