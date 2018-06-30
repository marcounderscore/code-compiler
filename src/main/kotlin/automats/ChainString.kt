package com.darkovr.automat


object ChainString {
    private var result: Boolean = false

    @JvmStatic
    fun init(data: String): Boolean {
        q0(data,0)
        return isResult()
    }

    private fun q0(data: String, position: Int) {
        if (position<data.length){
            println("q0 Position: "+position+" Data: "+data[position])
            when (data[position]) {
                '#' -> q1(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q1(data: String, position: Int) {
        if (position<data.length){
            println("q1 Position: "+position+" Data: "+data[position])
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
                '0' -> q1(data,position+1)
                '1' -> q1(data,position+1)
                '2' -> q1(data,position+1)
                '3' -> q1(data,position+1)
                '4' -> q1(data,position+1)
                '5' -> q1(data,position+1)
                '6' -> q1(data,position+1)
                '7' -> q1(data,position+1)
                '8' -> q1(data,position+1)
                '9' -> q1(data,position+1)
                '#' -> setResult(true)
                else -> setResult(false)
            }
        }
    }

    private fun isResult(): Boolean {
        return result
    }

    private fun setResult(result: Boolean) {
        this.result = result
    }
}