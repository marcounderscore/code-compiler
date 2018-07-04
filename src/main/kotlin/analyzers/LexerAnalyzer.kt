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
                        Constants.BOOLEAN_TYPE.forEach { item ->
                            when (item) {
                                token -> category = Constants.BOOLEAN
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
                        fileHandler.write(Register(token,"",0,"",category,HashAlgorithm.getHash(token)))
                        //App.registerList.add(Register(token,"",0,"",category,HashAlgorithm.getHash(token)))
                    }else{
                        App.errorList.add(Error(token,Constants.LEXER_ERROR,Constants.TOKEN_NOT_FOUND_EXCEPTION,lineCounter)).toString()
                        println("Lexer error: $token Token no reconocido")
                    }
                }
            }
        }
    }
}