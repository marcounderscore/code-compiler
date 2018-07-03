import analyzers.LexerAnalyzer
import com.darkovr.automat.ChainString
import files.FileHandler
import javafx.application.Application
import tornadofx.App
import views.Interface

class MyApp: App(Interface::class)
fun main(args: Array<String>) {
    //Application.launch(MyApp::class.java,*args)
    //val lexerAnalyzer = LexerAnalyzer()
    //val fileHandler = FileHandler()
    //lexerAnalyzer.analyzeLine("class main { fun test ( ) : Int { Int value = 5 + 3 ; Float value = 5.5 ; if ( value < value2 ) { value = 20 ; } return value } }")
    //fileHandler.read()
    println(ChainString.init("# Entre mas sean mejor #"))
}