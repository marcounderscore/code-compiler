package analyzers

import com.darkovr.automat.*
import files.FileHandler
import hash.HashAlgorithm
import models.Register
import views.Interface
import views.Test
import java.util.*

class LexerAnalyzer {
    fun analyzeLine(data: String){
        val line = StringTokenizer(data)
        var errors = ""
        while (line.hasMoreTokens()) {
            var category = ""
            val token = line.nextToken()
            Constants.RESERVED_WORDS_LIST.forEach { item ->
                when (item) {
                    token -> category = Constants.RESERVED_WORD
                }
            }
            if (category == ""){
                Constants.DELIMITER_LIST.forEach { item ->
                    when (item) {
                        token -> category = Constants.DELIMITER
                    }
                }
            }
            if (category == ""){
                Constants.OPERATOR_LIST.forEach { item ->
                    when (item) {
                        token -> category = Constants.OPERATOR
                    }
                }
            }
            if (category == ""){
                category = when {
                    DecimalDigits.init(token) -> Constants.DECIMAL_DIGIT
                    ChainString.init(token) -> Constants.CHAIN_STRING
                    Comment.init(token) -> Constants.COMMENT
                    Identifier.init(token) -> Constants.IDENTIFIER
                    IntegerDigits.init(token) -> Constants.INTEGER_DIGIT
                    else -> "Token no reconocido"
                }
            }
            if (category != "Token no reconocido"){
                val fileHandler = FileHandler()
                fileHandler.write(Register(token,"",0,"",category,HashAlgorithm.getHash(token)))
            }else{
                errors += "Lexer error: $token $category \n"
                println("Lexer error: $token $category")
            }
        }
    }
}