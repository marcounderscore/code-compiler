import javafx.application.Application
import tornadofx.*

class MyApp: App(Interface::class)
fun main(args: Array<String>) {
    Application.launch(MyApp::class.java,*args)
}