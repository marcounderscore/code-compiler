package analyzers

import Mutables
import models.Register
import models.SyntacticDebugTable
import java.util.*
import kotlin.collections.ArrayList


class SyntacticAnalyzer {
    fun makeSyntacticAnalysis(){

        /*for (i in 0 until 10)
            printStack(Mutables.codeStack.peek().token)*/

        while (Mutables.codeStack.peek() != "$") {
            if (Mutables.codeStack.peek() != Mutables.inputStack.peek()){
                val rule = getRule(Mutables.codeStack.peek(), Mutables.inputStack.peek())
                //println("codeStack: "+Mutables.codeStack.toString()+", inputStack:  "+Mutables.inputStack.toString())
                //println("Rule: "+rule)
                printStacks()
                if (!rule.isEmpty()){
                    Mutables.codeStack.pop()
                    if (rule[0] != "ε"){
                        Collections.reverse(rule)
                        rule.forEach {
                            Mutables.codeStack.push(it)
                        }
                        Collections.reverse(rule)
                    }
                }else{
                    println("Error sintactico")
                    break
                }
            }else{
                //println("codeStack: "+Mutables.codeStack.toString()+", inputStack:  "+Mutables.inputStack.toString())
                printStacks()
                Mutables.codeStack.pop()
                Mutables.inputStack.pop()
            }
        }
        if (Mutables.codeStack.peek() == "$") {
            printStacks()
            //println("Lo has hecho bien :')")
        }
    }

    private fun getRule(stack: String, input: String): ArrayList<String>{
        var array = ArrayList<String>()
        Mutables.syntacticDynamicList.forEach { item ->
            val production = item.find { it.terminal == "name" && it.data[0] == stack }
            if (production != null){
                val element = item.find { it.terminal == input }
                if (element != null){
                    array = element.data
                }
            }
        }
        return array
    }

    private fun printStacks(){
        var input = ""
        var stack = ""
        val cloneInput = Mutables.inputStack.clone() as Stack<String>
        while (!cloneInput.empty()){
            input += cloneInput.peek()+" "
            cloneInput.pop()
        }
        Mutables.codeStack.forEach {
            stack += it+" "
        }
        Mutables.syntacticDebugList.add(SyntacticDebugTable(stack,input))
    }
}