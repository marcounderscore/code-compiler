package analyzers

import Constants
import com.darkovr.automat.*
import files.FileHandler
import hash.HashAlgorithm
import models.Error
import models.Register
import java.util.*

class LexerAnalyzer {
    fun analyzeLine(data: List<String>){
        var lineCounter = 0
        for (i in data.indices) {
            var isComment = false
            var tempToken = ""
            lineCounter++
            val line = StringTokenizer(data[i])
            loop@ while (line.hasMoreTokens()) {
                var category = Constants.NULL
                var token = line.nextToken()

                if (token == "#"){
                    if (isComment){
                        tempToken += "#"
                        token = tempToken
                        tempToken = ""
                    }
                    isComment = !isComment
                }
                if (isComment){
                    tempToken += token+" "
                }

                if (!isComment){
                    Constants.RESERVED_WORDS_LIST.forEach { item ->
                        when (item) {
                            token -> category = Constants.RESERVED_WORD
                        }
                    }
                    if (category == Constants.NULL){
                        Constants.DELIMITER_LIST.forEach { item ->
                            when (item) {
                                token -> category = Constants.DELIMITER
                            }
                        }
                    }
                    if (category == Constants.NULL){
                        Constants.OPERATOR_LIST.forEach { item ->
                            when (item) {
                                token -> category = Constants.OPERATOR
                            }
                        }
                    }
                    if (category == Constants.NULL){
                        category = when {
                            DecimalDigits.init(token) -> Constants.DECIMAL_DIGIT
                            ChainString.init(token) -> Constants.CHAIN_STRING
                            Comment.init(token) -> {
                                Constants.COMMENT
                                break@loop
                            }
                            Identifier.init(token) -> Constants.IDENTIFIER
                            IntegerDigits.init(token) -> Constants.INTEGER_DIGIT
                            else -> Constants.NULL
                        }
                    }
                    if (category != Constants.NULL){
                        val fileHandler = FileHandler()
                        fileHandler.write(Register(token,getType(category,token),getSize(category,token),"",category,HashAlgorithm.getHash(token),getTyping(category,token)))
                        pushInput(token,category)
                    }else{
                        Mutables.errorList.add(Error(token,Constants.LEXER_ERROR,Constants.TOKEN_NOT_FOUND_EXCEPTION,lineCounter)).toString()
                        println("Lexer error: $token Token no reconocido")
                    }
                }
            }
            if(isComment){
                Mutables.errorList.add(Error(tempToken,Constants.LEXER_ERROR,Constants.COMMENT_FORMAT_EXCEPTION,lineCounter)).toString()
            }
        }
    }

    private fun getType(category: String, token: String): String{
        var type = ""
        when (category){
            Constants.RESERVED_WORD -> {
                Constants.BOOL_LIST.forEach { item ->
                    when (item) {
                        token -> type = Constants.BOOLEAN
                    }
                }
            }
            Constants.IDENTIFIER -> type = ""
            Constants.OPERATOR -> type = ""
            Constants.DECIMAL_DIGIT -> type = Constants.FLOAT
            Constants.INTEGER_DIGIT -> type = Constants.INTEGER
            Constants.CHAIN_STRING -> type = Constants.STRING
            Constants.COMMENT -> type = ""
            Constants.DELIMITER -> type = ""
        }
        return type
    }

    private fun getSize(category: String, token: String): Int{
        var size = 0
        when (category){
            Constants.RESERVED_WORD -> {
                Constants.BOOL_LIST.forEach { item ->
                    when (item) {
                        token -> size = Constants.BOOL_SIZE
                    }
                }
            }
            Constants.IDENTIFIER -> size = 0
            Constants.OPERATOR -> size = 0
            Constants.DECIMAL_DIGIT -> size = Constants.FLOAT_SIZE
            Constants.INTEGER_DIGIT -> size = Constants.INT_SIZE
            Constants.CHAIN_STRING -> size = token.length-4
            Constants.COMMENT -> size = 0
            Constants.DELIMITER -> size = 0
        }
        return size
    }

    private fun getTyping(category: String, token: String): String{
        var typing = ""
        when (category){
            Constants.RESERVED_WORD -> {
                Constants.BOOL_LIST.forEach { item ->
                    when (item) {
                        token -> typing = Constants.CONSTANT
                    }
                }
            }
            Constants.IDENTIFIER -> typing = ""
            Constants.OPERATOR -> typing = ""
            Constants.DECIMAL_DIGIT -> typing = Constants.CONSTANT
            Constants.INTEGER_DIGIT -> typing = Constants.CONSTANT
            Constants.CHAIN_STRING -> typing = Constants.CONSTANT
            Constants.COMMENT -> typing = ""
            Constants.DELIMITER -> typing = ""
        }
        return typing
    }

    private fun pushInput(token: String, category: String){
        when (category){
            Constants.IDENTIFIER -> Mutables.inputStack.push(Constants.IDENTIFIER)
            Constants.DECIMAL_DIGIT -> Mutables.inputStack.push(Constants.DECIMAL_DIGIT)
            Constants.INTEGER_DIGIT -> Mutables.inputStack.push(Constants.INTEGER_DIGIT)
            Constants.CHAIN_STRING -> Mutables.inputStack.push(Constants.CHAIN_STRING)
            else -> Mutables.inputStack.push(token)
        }
    }
}