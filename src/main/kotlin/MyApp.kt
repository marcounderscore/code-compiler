import com.darkovr.automat.ChainString
import files.FileHandler
import hash.HashAlgorithm
import tornadofx.App
import views.Interface

class MyApp: App(Interface::class)
fun main(args: Array<String>) {
    //Application.launch(MyApp::class.java,*args)
    val fileHandler = FileHandler()
    //fileHandler.write(Register("x", "entero", 4, "*", "pr",0))
    //fileHandler.write(Register("y", "entero", 4, "*", "pr",1))
    //fileHandler.read()
    //println(HashAlgorithm.getHash("int"))
    println(ChainString.init("#eerere#"))
}