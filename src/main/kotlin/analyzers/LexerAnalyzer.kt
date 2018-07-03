package analyzers

import Constants
import com.darkovr.automat.*
import files.FileHandler
import hash.HashAlgorithm
import models.Error
import models.Register
import java.util.*

class LexerAnalyzer {
    fun analyzeLine(data: String){
        val line = StringTokenizer(data)
        while (line.hasMoreTokens()) {
            var category = Constants.NULL
            val token = line.nextToken()
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
                    Comment.init(token) -> Constants.COMMENT
                    Identifier.init(token) -> Constants.IDENTIFIER
                    IntegerDigits.init(token) -> Constants.INTEGER_DIGIT
                    else -> Constants.NULL
                }
            }
            if (category != Constants.NULL){
                val fileHandler = FileHandler()
                fileHandler.write(Register(token,"",0,"",category,HashAlgorithm.getHash(token)))
            }else{
                App.errorList.add(Error(token,"Lexer error","Token no encontrado",0)).toString()
                println("Lexer error: $token Token no reconocido")
            }
        }
    }
}