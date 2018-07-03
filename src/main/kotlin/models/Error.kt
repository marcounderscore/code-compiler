package models

data class Error (
    val token: String,
    val errorType: String,
    val description: String,
    val line: Int
){
    val init: String = "Error:"
}