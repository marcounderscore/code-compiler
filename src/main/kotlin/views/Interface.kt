package views

import Constants
import Mutables
import analyzers.LexerAnalyzer
import analyzers.SyntacticAnalyzer
import files.FileHandler
import files.SyntacticTableFile
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.paint.Color
import models.Register
import models.SAT
import models.SyntacticDebugTable
import tornadofx.*
import kotlin.concurrent.thread

class Interface: View() {
    override val root = borderpane {
        left<EditorView>()
        right<SymbolsTable>()
        bottom<ErrorsView>()
    }
}

class EditorView : View() {
    private val controller: MyController by inject()
    private val errorView: ErrorsView by inject()
    private val tableView: SymbolsTable by inject()
    private val editorInput = SimpleStringProperty()
    private val syntacticObservables = FXCollections.observableArrayList<SyntacticDebugTable>()!!
    private var outputStr = ""

    override val root = form {

        //Fill the syntactic analysis table
        runAsync {
            Mutables.syntacticTableList.clear()
            controller.fillSyntacticTable()
            //controller.printList()
        } ui {
            val syntacticList = Mutables.syntacticTableList.observable()
            tableView.syntacticList.asyncItems { syntacticList }
        }

        fieldset {
            button("Compilar") {
                action {
                    progressbar {
                        thread {
                            for (i in 1..100) {
                                Platform.runLater { progress = i.toDouble() / 100.0 }
                                Thread.sleep(10)
                            }

                            //clean errors list
                            Mutables.errorList.clear()
                            //initStacks
                            controller.initStacks()
                            //Make lexer analysis and fill input stack for further syntactic analysis
                            controller.makeAnalysis(editorInput.value.split("\n"))

                            //Debugging for errors output
                            outputStr = ""
                            runAsync {
                                Mutables.errorList.forEach { item ->
                                    outputStr += item.init+" "+item.errorType+", '"+item.token+"' "+item.description+", line "+item.line+"\n"
                                }
                            } ui {
                                errorView.errorOutput.value = outputStr
                                println("ERROR_LIST_SIZE: "+Mutables.errorList.size)
                            }

                            //Fill the lexer analysis table
                            runAsync {
                                Mutables.lexerTableList.clear()
                                controller.readFromFile()
                            } ui {
                                val registerList = Mutables.lexerTableList.observable()
                                tableView.registerList.asyncItems { registerList }
                            }

                            //Debugging for syntactic debug table
                            outputStr = ""
                            runAsync {
                                Mutables.syntacticDebugList.clear()
                                controller.makeSyntacticAnalysis()
                                Mutables.errorList.forEach { item ->
                                    outputStr += item.init+" "+item.errorType+", '"+item.token+"' "+item.description+", line "+item.line+"\n"
                                }
                            } ui {
                                val syntacticAnalysis = Mutables.syntacticDebugList.observable()
                                syntacticObservables.asyncItems { syntacticAnalysis }
                                errorView.errorOutput.value = outputStr
                                println("ERROR_LIST_SIZE: "+Mutables.errorList.size)
                            }
                        }
                    }
                }
            }

            label("Editor") {
                textFill = Color.BLUE
            }
            field {
                textarea(editorInput)
                //textarea(input).text = "& er4 sds // %$ Int class main { fun test ( ) : Int { Int value = 5 + 3 ; Float value = 5.5 ; if ( value < value2 ) { value = 20 ; } return value } }"
            }

            label("Syntactic debugger") {
                textFill = Color.BROWN
            }

            setMaxSize(550.0,500.0)
            tableview(syntacticObservables) {
                readonlyColumn("Stack",SyntacticDebugTable::pile).minWidth = 700.0
                readonlyColumn("Input",SyntacticDebugTable::input).minWidth = 700.0
            }
        }

    }
}

class MyController: Controller() {
    private fun makeLexerAnalysis(inputValue: List<String>){
        val lexerAnalyzer = LexerAnalyzer()
        lexerAnalyzer.analyzeLine(inputValue)
    }
    fun makeSyntacticAnalysis(){
        val syntacticAnalysis = SyntacticAnalyzer()
        syntacticAnalysis.makeSyntacticAnalysis()
    }
    fun readFromFile(){
        val fileHandler = FileHandler()
        fileHandler.read()
    }
    fun fillSyntacticTable(){
        val syntacticFile = SyntacticTableFile()
        syntacticFile.getData()
    }
    fun initStacks(){
        Mutables.codeStack.clear()
        Mutables.inputStack.clear()
        //Mutables.inputStack.push("$")
        Mutables.codeStack.push("$")
        Mutables.codeStack.push("APP")
    }
    fun makeAnalysis(inputValue: List<String>){
        Constants.file.delete()
        makeLexerAnalysis(inputValue)
    }

    fun printList(){
        Mutables.syntacticDynamicList.forEach { item ->
            item.forEach {
                print(it.terminal+": ")
                println(it.data.toString())
            }
        }
    }
}

class ErrorsView: View() {
    val errorOutput = SimpleStringProperty()

    override val root = form {

        fieldset{
            label("Errors") {
                textFill = Color.RED
            }
            field {
                textarea(errorOutput).maxHeight = 150.0
            }
        }
    }
}

class SymbolsTable: View() {
    val registerList = FXCollections.observableArrayList<Register>()!!
    val syntacticList = FXCollections.observableArrayList<SAT>()!!

    override val root = form {

        style { setMaxSize(802.0,500.0) }

        label("Symbols table") {
            textFill = Color.BROWN
        }
        tableview(registerList) {
            readonlyColumn("Token",Register::token).minWidth = 300.0
            readonlyColumn("Type",Register::type)
            readonlyColumn("Size",Register::size)
            readonlyColumn("Value",Register::value)
            readonlyColumn("Category",Register::category)
            readonlyColumn("Position",Register::position)
            readonlyColumn("Typing",Register::typing)
        }

        label("Syntactic analysis table") {
            textFill = Color.BROWN
        }
        tableview(syntacticList) {
            readonlyColumn("name",SAT::_name).minWidth = 300.0
            readonlyColumn("class",SAT::_class).minWidth = 300.0
            readonlyColumn("id",SAT::_id).minWidth = 300.0
            readonlyColumn("{",SAT::_ob).minWidth = 300.0
            readonlyColumn("}",SAT::_cb).minWidth = 300.0
            readonlyColumn(",",SAT::_coma).minWidth = 300.0
            readonlyColumn("=",SAT::_equal).minWidth = 300.0
            readonlyColumn("val",SAT::_val).minWidth = 300.0
            readonlyColumn("var",SAT::_var).minWidth = 300.0
            readonlyColumn("de",SAT::_de).minWidth = 300.0
            readonlyColumn("dd",SAT::_dd).minWidth = 300.0
            readonlyColumn("cs",SAT::_cs).minWidth = 300.0
            readonlyColumn("true",SAT::_true).minWidth = 300.0
            readonlyColumn("false",SAT::_false).minWidth = 300.0
            readonlyColumn("String",SAT::_String).minWidth = 300.0
            readonlyColumn("Bool",SAT::_Bool).minWidth = 300.0
            readonlyColumn("Int",SAT::_Int).minWidth = 300.0
            readonlyColumn("Float",SAT::_Float).minWidth = 300.0
            readonlyColumn("fun",SAT::_fun).minWidth = 300.0
            readonlyColumn("(",SAT::_op).minWidth = 300.0
            readonlyColumn(")",SAT::_cp).minWidth = 300.0
            readonlyColumn(":",SAT::_colon).minWidth = 300.0
            readonlyColumn("return",SAT::_return).minWidth = 300.0
            readonlyColumn("empty",SAT::_empty).minWidth = 300.0
            readonlyColumn("if",SAT::_if).minWidth = 300.0
            readonlyColumn("else",SAT::_else).minWidth = 300.0
            readonlyColumn("while",SAT::_while).minWidth = 300.0
            readonlyColumn("for",SAT::_for).minWidth = 300.0
            readonlyColumn("until",SAT::_until).minWidth = 300.0
            readonlyColumn("in",SAT::_in).minWidth = 300.0
            readonlyColumn("call",SAT::_call).minWidth = 300.0
            readonlyColumn("+",SAT::_plus).minWidth = 300.0
            readonlyColumn("-",SAT::_minus).minWidth = 300.0
            readonlyColumn("/",SAT::_div).minWidth = 300.0
            readonlyColumn("and",SAT::_and).minWidth = 300.0
            readonlyColumn("or",SAT::_or).minWidth = 300.0
            readonlyColumn(">",SAT::_gt).minWidth = 300.0
            readonlyColumn("<",SAT::_lt).minWidth = 300.0
            readonlyColumn("!=",SAT::_ne).minWidth = 300.0
            readonlyColumn("==",SAT::_dequal).minWidth = 300.0
        }
    }
}