package models

data class Register(
        val token: String,
        val type: String,
        val size: Int,
        val value: String,
        val category: String,
        val position: Long,
        val typing: String
) {
    val registerSize: Long = 324
}