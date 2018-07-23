package files

import models.SAT
import models.Terminal
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.util.ArrayList

class SyntacticTableFile {
    fun getData(){
        val jsonFile = JSONParser().parse(Constants.syntacticFile) as JSONArray

        for ((i, data) in jsonFile.withIndex()) {
            val production = data as JSONObject

            Mutables.syntacticDynamicList.add(ArrayList())

            val name = production["name"] as String
            Mutables.syntacticDynamicList[i].add(Terminal("name", arrayListOf(name)))

            val idArray = production["id"] as JSONArray
            val id = getData(idArray)
            Mutables.syntacticDynamicList[i].add(Terminal("id",getArray(idArray)))

            val plusArray = production["+"] as JSONArray
            val plus = getData(plusArray)
            Mutables.syntacticDynamicList[i].add(Terminal("+",getArray(plusArray)))

            val byArray = production["*"] as JSONArray
            val by = getData(byArray)
            Mutables.syntacticDynamicList[i].add(Terminal("*",getArray(byArray)))

            val opArray = production["("] as JSONArray
            val op = getData(opArray)
            Mutables.syntacticDynamicList[i].add(Terminal("(",getArray(opArray)))

            val cpArray = production[")"] as JSONArray
            val cp = getData(cpArray)
            Mutables.syntacticDynamicList[i].add(Terminal(")",getArray(cpArray)))

            val signArray = production["$"] as JSONArray
            val sign = getData(signArray)
            Mutables.syntacticDynamicList[i].add(Terminal("$",getArray(signArray)))

            Mutables.syntacticTableList.add(SAT(name,id,plus,by,op,cp,sign))
        }
    }

    private fun getData(jsonArray: JSONArray): String{
        var data = ""
        for (item in jsonArray) {
            data += item.toString()+", "
        }
        return data
    }

    private fun getArray(jsonArray: JSONArray): ArrayList<String>{
        val dataList = jsonArray.mapTo(ArrayList()) { it.toString() }
        return dataList
    }
}