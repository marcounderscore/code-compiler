package com.darkovr.automat

object Identifier {
    private var result: Boolean = false

    @JvmStatic
    fun init(data: String): Boolean {
        result = false
        q0(data,0)
        return isResult()
    }

    private fun q0(data: String, position: Int) {
        if (position<data.length){
            //println("q0 Position: "+position+" Data: "+data[position])
            when (data[position]) {
                'a' -> q1(data,position+1)
                'b' -> q1(data,position+1)
                'c' -> q1(data,position+1)
                'd' -> q1(data,position+1)
                'e' -> q1(data,position+1)
                'f' -> q1(data,position+1)
                'g' -> q1(data,position+1)
                'h' -> q1(data,position+1)
                'i' -> q1(data,position+1)
                'j' -> q1(data,position+1)
                'k' -> q1(data,position+1)
                'l' -> q1(data,position+1)
                'm' -> q1(data,position+1)
                'n' -> q1(data,position+1)
                'o' -> q1(data,position+1)
                'p' -> q1(data,position+1)
                'q' -> q1(data,position+1)
                'r' -> q1(data,position+1)
                's' -> q1(data,position+1)
                't' -> q1(data,position+1)
                'u' -> q1(data,position+1)
                'v' -> q1(data,position+1)
                'w' -> q1(data,position+1)
                'x' -> q1(data,position+1)
                'y' -> q1(data,position+1)
                'z' -> q1(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q1(data: String, position: Int) {
        result = true
        if (position<data.length){
            //println("q1 Position: "+position+" Data: "+data[position])
            when (data[position].toUpperCase()) {
                'A' -> q1(data,position+1)
                'B' -> q1(data,position+1)
                'C' -> q1(data,position+1)
                'D' -> q1(data,position+1)
                'E' -> q1(data,position+1)
                'F' -> q1(data,position+1)
                'G' -> q1(data,position+1)
                'H' -> q1(data,position+1)
                'I' -> q1(data,position+1)
                'J' -> q1(data,position+1)
                'K' -> q1(data,position+1)
                'L' -> q1(data,position+1)
                'M' -> q1(data,position+1)
                'N' -> q1(data,position+1)
                'O' -> q1(data,position+1)
                'P' -> q1(data,position+1)
                'Q' -> q1(data,position+1)
                'R' -> q1(data,position+1)
                'S' -> q1(data,position+1)
                'T' -> q1(data,position+1)
                'U' -> q1(data,position+1)
                'V' -> q1(data,position+1)
                'W' -> q1(data,position+1)
                'X' -> q1(data,position+1)
                'Y' -> q1(data,position+1)
                'Z' -> q1(data,position+1)
                '0' -> q2(data,position+1)
                '1' -> q2(data,position+1)
                '2' -> q2(data,position+1)
                '3' -> q2(data,position+1)
                '4' -> q2(data,position+1)
                '5' -> q2(data,position+1)
                '6' -> q2(data,position+1)
                '7' -> q2(data,position+1)
                '8' -> q2(data,position+1)
                '9' -> q2(data,position+1)
                else -> result = false
            }
        }
        setResult(result)
    }

    private fun q2(data: String, position: Int) {
        result = true
        if (position<data.length){
            //println("q2 Position: "+position+" Data: "+data[position])
            when (data[position]) {
                '0' -> q2(data,position+1)
                '1' -> q2(data,position+1)
                '2' -> q2(data,position+1)
                '3' -> q2(data,position+1)
                '4' -> q2(data,position+1)
                '5' -> q2(data,position+1)
                '6' -> q2(data,position+1)
                '7' -> q2(data,position+1)
                '8' -> q2(data,position+1)
                '9' -> q2(data,position+1)
                else -> result = false
            }
        }
        setResult(result)
    }

    private fun isResult(): Boolean {
        return result
    }

    private fun setResult(result: Boolean) {
        this.result = result
    }
}