import models.*
import java.util.*
import java.util.ArrayList



object Mutables {
    @JvmStatic
    val lexerTableList = ArrayList<Register>()
    @JvmStatic
    val errorList = ArrayList<Error>()
    @JvmStatic
    val syntacticTableList = ArrayList<SAT>()
    @JvmStatic
    val codeStack = Stack<String>()
    @JvmStatic
    val inputStack = Stack<String>()
    @JvmStatic
    val syntacticDebugList = ArrayList<SyntacticDebugTable>()
    @JvmStatic
    val syntacticDynamicList = ArrayList<ArrayList<Terminal>>()
}