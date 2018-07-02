package com.darkovr.automat

object Comment {
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
                '/' -> q1(data,position+1)
                else -> setResult(result)
            }
        }
    }

    private fun q1(data: String, position: Int) {
        if (position<data.length){
            //println("q1 Position: "+position+" Data: "+data[position])
            when (data[position]) {
                '/' -> setResult(true)
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