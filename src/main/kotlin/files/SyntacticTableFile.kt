package files

import models.SAT
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser

class SyntacticTableFile {
    fun getData(){
        val jsonFile = JSONParser().parse(Constants.syntacticFile) as JSONArray

        for (data in jsonFile) {
            val production = data as JSONObject

            val name = production["name"] as String
            val id = getData(production["id"] as JSONArray)
            val plus = getData(production["+"] as JSONArray)
            val by = getData(production["*"] as JSONArray)
            val op = getData(production["("] as JSONArray)
            val cp = getData(production[")"] as JSONArray)
            val sign = getData(production["$"] as JSONArray)

            Mutables.syntacticList.add(SAT(name,id,plus,by,op,cp,sign))
        }
    }

    private fun getData(jsonArray: JSONArray): String{
        var data = ""
        for (item in jsonArray) {
            data += item.toString()+", "
        }
        return data
    }
}