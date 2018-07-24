package analyzers

import Constants
import Mutables
import models.Error
import models.SyntacticDebugTable
import models.Token
import java.util.*
import kotlin.collections.ArrayList


class SyntacticAnalyzer {
    fun makeSyntacticAnalysis(){
        Mutables.inputStack.push(Token("$",1))
        Collections.reverse(Mutables.inputStack)
        while (Mutables.codeStack.peek() != "$") {
            if (Mutables.codeStack.peek() != Mutables.inputStack.peek().token){
                //Return the rule finding into the syntactic analysis table json file sending both stack's peeks
                val rule = getRule(Mutables.codeStack.peek(), Mutables.inputStack.peek().token)
                printStacks() //Print the stacks in the interface
                if (!rule.isEmpty()){ //If the rule exists pop the codeStack
                    Mutables.codeStack.pop()
                    if (rule[0] != "ε"){ // if the rule is not empty codeStack updates with rule elements
                        Collections.reverse(rule)
                        rule.forEach {
                            Mutables.codeStack.push(it)
                        }
                        Collections.reverse(rule)
                    }
                }else{  //The analysis found an error, there is no rule for the input
                    Mutables.errorList.add(Error("Rule near token: "+Mutables.inputStack.peek().token,Constants.SYNTACTIC_ERROR,Constants.RULE_NOT_FOUND_EXCEPTION,Mutables.inputStack.peek().line)).toString()
                    break
                }
            }else{ //there are the same terminal in both stacks
                printStacks()
                Mutables.codeStack.pop()
                Mutables.inputStack.pop()
            }
        }
        if (Mutables.codeStack.peek() == "$") { //The code stack is '$', so it's finished and right
            printStacks()
        }
    }

    private fun getRule(stack: String, input: String): ArrayList<String>{
        var array = ArrayList<String>()
        Mutables.syntacticDynamicList.forEach { item -> //Find in every file of the table
            val production = item.find { it.terminal == "name" && it.data[0] == stack }
            if (production != null){ //if the production name exists
                val element = item.find { it.terminal == input }
                if (element != null){ //if the column name exists
                    array = element.data
                }
            }
        }
        return array
    }

    private fun printStacks(){
        var input = ""
        var stack = ""
        val cloneInput = Mutables.inputStack.clone() as Stack<Token>
        while (!cloneInput.empty()){
            input += cloneInput.peek().token+" "
            cloneInput.pop()
        }
        Mutables.codeStack.forEach {
            stack += it+" "
        }
        Mutables.syntacticDebugList.add(SyntacticDebugTable(stack,input))
    }
}