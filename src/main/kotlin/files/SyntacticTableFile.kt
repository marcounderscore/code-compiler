package files

import Constants
import Mutables
import models.SAT
import javax.xml.parsers.DocumentBuilderFactory


class SyntacticTableFile {
    fun getData(){
        val documentBuilderFactory = DocumentBuilderFactory.newInstance()
        val documentBuilder = documentBuilderFactory.newDocumentBuilder()
        val document = documentBuilder.parse(Constants.syntacticFile)

        var i = 0
        while (i<document.getElementsByTagName("production").length){
            val name = document.getElementsByTagName("name").item(i).textContent
            val id = document.getElementsByTagName("id").item(i).textContent
            val plus = document.getElementsByTagName("plus").item(i).textContent
            val by = document.getElementsByTagName("by").item(i).textContent
            val openParenthesis = document.getElementsByTagName("open-parenthesis").item(i).textContent
            val closeParenthesis = document.getElementsByTagName("close-parenthesis").item(i).textContent
            val sign = document.getElementsByTagName("sign").item(i).textContent
            Mutables.syntacticList.add(SAT(name,id,plus,by,openParenthesis,closeParenthesis,sign))
            i++
        }
    }
}