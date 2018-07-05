import java.io.File

object Constants {
    val RESERVED_WORDS_LIST = arrayOf("class","fun","val","var","Int","String","Bool","Float","if","else","for","while","and","or","return")
    val OPERATOR_LIST = arrayOf("+","-","*","/","=",">","<","==","=!")
    val DELIMITER_LIST = arrayOf(";","{","}",":","(",")",",")
    val BOOLEAN_TYPE = arrayOf("true","false")
    val RESERVED_WORD = "PR"
    val IDENTIFIER = "ID"
    val OPERATOR = "OP"
    val DECIMAL_DIGIT = "DD"
    val INTEGER_DIGIT = "DE"
    val CHAIN_STRING = "CS"
    val COMMENT = "COM"
    val DELIMITER = "DEL"
    val BOOLEAN = "Bool"
    val NULL = "NULL"
    val LEXER_ERROR = "Lexer Error"
    val SYNTACTIC_ERROR = "Syntactic Error"
    val SEMANTIC_ERROR = "Semantic Error"
    val TOKEN_NOT_FOUND_EXCEPTION = "Token not found exception"
    val DECIMAL_FORMAT_EXCEPTION = "Decimal format exception"
    val NUMBER_FORMAT_EXCEPTION = "Number format exception"
    val COMMENT_FORMAT_EXCEPTION = "Comment format exception"
    val IDENTIFIER_FORMAT_EXCEPTION = "Identifier format exception"
    val INT_SIZE = 4
    val FLOAT_SIZE = 4
    val file = File("symbols_table")
}