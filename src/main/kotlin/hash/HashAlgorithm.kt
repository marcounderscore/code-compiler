package hash

object HashAlgorithm {
    @JvmStatic
    fun getHash(str: String): Long {
        var hash = str.length.toLong()
        for (i in 0 until str.length) {
            hash = hash shl 5 xor (hash shr 27) xor str[i].toLong()
        }
        hash %= 353
        return hash
    }
}