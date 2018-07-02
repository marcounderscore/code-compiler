package views

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Interface : View() {
    private val controller: MyController by inject()
    private val input = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Input") {
                textfield(input)
            }
            field("Data"){
                textarea(input)
            }

            val textfield = textfield()
            button("Commit") {
                action {
                    controller.writeToDb(input.value)
                    input.value = ""

                    runAsync {
                        controller.loadText()
                    } ui { loadedText ->
                        textfield.text = loadedText
                    }
                }
            }
        }
    }
}


class MyController: Controller() {
    fun writeToDb(inputValue: String) {
        println("Writing $inputValue to database!")
    }
    fun loadText(): String {
        return "Hello"
    }
}
