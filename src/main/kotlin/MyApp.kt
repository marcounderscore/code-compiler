import files.FileHandler
import views.Interface
import models.Register
import tornadofx.*

class MyApp: App(Interface::class)
fun main(args: Array<String>) {
    //Application.launch(MyApp::class.java,*args)
    val fileHandler = FileHandler()
    fileHandler.write(Register("x", "entero", 4, "*", "pr",0))
    fileHandler.write(Register("y", "entero", 4, "*", "pr",1))
    fileHandler.read()
}