package analyzers

import models.SyntacticDebugTable
import java.util.*

class SyntacticAnalyzer {
    fun makeSyntacticAnalysis(){
        Collections.reverse(Mutables.codeList)



        var input = ""
        Mutables.codeList.forEach {
            input += it.token+" "
        }
        Mutables.syntacticDebugList.add(SyntacticDebugTable("$ E",input))
        Mutables.codeList.clear()
    }
}