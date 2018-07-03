package com.darkovr.automat


object ChainString {
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
                '#' -> q1(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q1(data: String, position: Int) {
        if (position<data.length){
            //println("q1 Position: "+position+" Data: "+data[position])
            when (data[position].toUpperCase().toInt()) {
                //  A..Z
                in 65..90 -> q1(data,position+1)
                //  0..9
                in 48..57 -> q1(data,position+1)
                ' '.toInt() -> q1(data,position+1)
                '#'.toInt() -> setResult(true)
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