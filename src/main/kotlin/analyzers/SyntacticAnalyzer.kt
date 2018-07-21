package analyzers

import Mutables
import models.SyntacticDebugTable

class SyntacticAnalyzer {
    fun makeSyntacticAnalysis(){

        var input = ""
        while (!Mutables.codeList.empty()){
            input += Mutables.codeList.peek().token+" "
            Mutables.codeList.pop()
        }
        Mutables.syntacticDebugList.add(SyntacticDebugTable("$ E",input))
        Mutables.codeList.clear()
    }

    fun testSyntacticTable(pile: String, input: String){

    }
}