import javafx.application.Application
import tornadofx.App
import views.Interface

class MyApp: App(Interface::class)
fun main(args: Array<String>) {
    Application.launch(MyApp::class.java,*args)
}