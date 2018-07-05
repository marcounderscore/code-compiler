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
                    /*if (category == Constants.NULL){
                        Constants.BOOLEAN_TYPE.forEach { item ->
                            when (item) {
                                token -> category = Constants.BOOLEAN
                            }
                        }
                    }*/
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
                        fileHandler.write(Register(token,getType(category),getSize(category,token),"",category,HashAlgorithm.getHash(token)))
                    }else{
                        App.errorList.add(Error(token,Constants.LEXER_ERROR,Constants.TOKEN_NOT_FOUND_EXCEPTION,lineCounter)).toString()
                        println("Lexer error: $token Token no reconocido")
                    }
                }
            }
            if(isComment){
                App.errorList.add(Error(tempToken,Constants.LEXER_ERROR,Constants.COMMENT_FORMAT_EXCEPTION,lineCounter)).toString()
            }
        }
    }

    private fun getType(category: String): String{
        var type = ""
        when (category){
            Constants.RESERVED_WORD -> type = "null"
            Constants.IDENTIFIER -> type = "null"
            Constants.OPERATOR -> type = "null"
            Constants.DECIMAL_DIGIT -> type = Constants.FLOAT
            Constants.INTEGER_DIGIT -> type = Constants.INTEGER
            Constants.CHAIN_STRING -> type = Constants.STRING
            Constants.COMMENT -> type = "null"
            Constants.DELIMITER -> type = "null"
        }
        return type
    }

    private fun getSize(category: String, token: String): Int{
        var size = 0
        when (category){
            Constants.RESERVED_WORD -> size = 0
            Constants.IDENTIFIER -> size = 0
            Constants.OPERATOR -> size = 0
            Constants.DECIMAL_DIGIT -> size = Constants.FLOAT_SIZE
            Constants.INTEGER_DIGIT -> size = Constants.INT_SIZE
            Constants.CHAIN_STRING -> size = token.length-4
            Constants.COMMENT -> size = 0
            Constants.OPERATOR -> size = 0
        }
        return size
    }
}