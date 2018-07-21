import models.Error
import models.Register
import models.SAT
import java.util.ArrayList

object Mutables {
    @JvmStatic
    val registerList = ArrayList<Register>()
    @JvmStatic
    val errorList = ArrayList<Error>()
    @JvmStatic
    val syntacticList = ArrayList<SAT>()
    @JvmStatic
    val codeList = ArrayList<Register>()
}