import java.io.File
import java.io.FileReader

object Constants {
    val RESERVED_WORDS_LIST = arrayOf("class","fun","val","var","Int","String","Bool","Float","if","else","for","while","and","or","return","true","false","empty","call","until","in")
    val OPERATOR_LIST = arrayOf("+","-","*","/","=",">","<","==","!=")
    val DELIMITER_LIST = arrayOf(";","{","}",":","(",")",",")
    val BOOL_LIST = arrayOf("true","false")
    val RESERVED_WORD = "pr"
    val IDENTIFIER = "id"
    val OPERATOR = "op"
    val DECIMAL_DIGIT = "dd"
    val INTEGER_DIGIT = "de"
    val CHAIN_STRING = "cs"
    val COMMENT = "com"
    val DELIMITER = "del"
    val BOOLEAN = "Bool"
    val INTEGER = "Int"
    val FLOAT = "Float"
    val STRING = "String"
    val NULL = "NULL"
    val CONSTANT = "Val"
    val VARIABLE = "Var"
    val LEXER_ERROR = "Lexer Error"
    val SYNTACTIC_ERROR = "Syntactic Error"
    val SEMANTIC_ERROR = "Semantic Error"
    val TOKEN_NOT_FOUND_EXCEPTION = "Token not found exception"
    val DECIMAL_FORMAT_EXCEPTION = "Decimal format exception"
    val NUMBER_FORMAT_EXCEPTION = "Number format exception"
    val COMMENT_FORMAT_EXCEPTION = "Comment format exception"
    val IDENTIFIER_FORMAT_EXCEPTION = "Identifier format exception"
    val RULE_NOT_FOUND_EXCEPTION = "Rule not found exception"
    val INT_SIZE = 4
    val FLOAT_SIZE = 4
    val BOOL_SIZE = 1
    val file = File("symbols_table")
    val syntacticFile = FileReader("syntactic_table.json")
}