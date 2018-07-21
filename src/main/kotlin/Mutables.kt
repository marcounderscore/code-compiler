import models.Error
import models.Register
import models.SAT
import models.SyntacticDebugTable
import java.util.*

object Mutables {
    @JvmStatic
    val lexerTableList = ArrayList<Register>()
    @JvmStatic
    val errorList = ArrayList<Error>()
    @JvmStatic
    val syntacticTableList = ArrayList<SAT>()
    @JvmStatic
    val codeList = Stack<Register>()
    @JvmStatic
    val syntacticDebugList = ArrayList<SyntacticDebugTable>()
}