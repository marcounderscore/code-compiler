import models.Error
import models.Register
import models.SAT
import java.util.ArrayList

object Mutables {
    @JvmStatic
    val lexerTableList = ArrayList<Register>()
    @JvmStatic
    val errorList = ArrayList<Error>()
    @JvmStatic
    val syntacticTableList = ArrayList<SAT>()
    @JvmStatic
    val codeList = ArrayList<Register>()
    @JvmStatic
    val syntacticDebugList = ArrayList<Register>()
}