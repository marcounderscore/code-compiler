package com.darkovr.automat

object DecimalDigits {
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
            when (data[position].toInt()) {
                //  0..9
                in 48..57 -> q1(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q1(data: String, position: Int) {
        if (position<data.length){
            //println("q1 Position: "+position+" Data: "+data[position])
            when (data[position].toInt()) {
                //  0..9
                in 48..57 -> q1(data,position+1)
                '.'.toInt() -> q2(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q2(data: String, position: Int) {
        if (position<data.length){
            //println("q0 Position: "+position+" Data: "+data[position])
            when (data[position].toInt()) {
                //  0..9
                in 48..57 -> q3(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q3(data: String, position: Int) {
        result = true
        if (position<data.length){
            //println("q1 Position: "+position+" Data: "+data[position])
            when (data[position].toInt()) {
                //  0..9
                in 48..57 -> q3(data,position+1)
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