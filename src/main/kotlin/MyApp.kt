import analyzers.LexerAnalyzer
import com.darkovr.automat.ChainString
import files.FileHandler
import javafx.application.Application
import tornadofx.App
import views.Interface

class MyApp: App(Interface::class)
fun main(args: Array<String>) {
    Application.launch(MyApp::class.java,*args)
    //val lexerAnalyzer = LexerAnalyzer()
    //val fileHandler = FileHandler()
    //lexerAnalyzer.analyzeLine("& er4 sds // \n %$ Int \n class main { \n fun test ( ) : Int { \n Int value = 5 + 3 ; \n Float value = 5.5 ; \n if ( value < value2 ) { value = 20 ; \n } \n return value \n } \n }")
    //fileHandler.read()
    //println(ChainString.init("# Entre mas sean mejor #"))
    //println("& er4 sds // \n %$ Int \n class main { \n fun test ( ) : Int { \n Int value = 5 + 3 ; \n Float value = 5.5 ; \n if ( value < value2 ) { value = 20 ; \n } \n return value \n } \n }".split("\n").size)
}