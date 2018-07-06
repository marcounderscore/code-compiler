import models.Error
import models.Register
import java.util.ArrayList

object Mutables {
    @JvmStatic
    val registerList = ArrayList<Register>()
    @JvmStatic
    val errorList = ArrayList<Error>()
}