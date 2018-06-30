package com.darkovr.automat

object DecimalDigits {
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
                else -> setResult(result)
            }
        }
    }

    private fun q1(data: String, position: Int) {
        if (position<data.length){
            println("q1 Position: "+position+" Data: "+data[position])
            when (data[position]) {
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
                '.' -> q2(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q2(data: String, position: Int) {
        if (position<data.length){
            println("q0 Position: "+position+" Data: "+data[position])
            when (data[position]) {
                '0' -> q3(data,position+1)
                '1' -> q3(data,position+1)
                '2' -> q3(data,position+1)
                '3' -> q3(data,position+1)
                '4' -> q3(data,position+1)
                '5' -> q3(data,position+1)
                '6' -> q3(data,position+1)
                '7' -> q3(data,position+1)
                '8' -> q3(data,position+1)
                '9' -> q3(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q3(data: String, position: Int) {
        result = true
        if (position<data.length){
            println("q1 Position: "+position+" Data: "+data[position])
            when (data[position]) {
                '0' -> q3(data,position+1)
                '1' -> q3(data,position+1)
                '2' -> q3(data,position+1)
                '3' -> q3(data,position+1)
                '4' -> q3(data,position+1)
                '5' -> q3(data,position+1)
                '6' -> q3(data,position+1)
                '7' -> q3(data,position+1)
                '8' -> q3(data,position+1)
                '9' -> q3(data,position+1)
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