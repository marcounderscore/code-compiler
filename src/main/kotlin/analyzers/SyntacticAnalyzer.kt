package analyzers

class SyntacticAnalyzer {
    fun makeSyntacticAnalysis(){
        for (data in Mutables.codeList) {
            println(data.token)
        }
    }
}