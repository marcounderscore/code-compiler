package files

import Constants
import Mutables
import models.SAT
import models.Terminal
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.util.*

class SyntacticTableFile {
    fun getData(){
        val jsonFile = JSONParser().parse(Constants.syntacticFile) as JSONArray

        var array: JSONArray
        for ((i, data) in jsonFile.withIndex()) {
            val production = data as JSONObject

            Mutables.syntacticDynamicList.add(ArrayList())

            val name = production["name"] as String
            Mutables.syntacticDynamicList[i].add(Terminal("name", arrayListOf(name)))

            array = production["class"] as JSONArray
            val _class = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("class",getArray(array)))

            array = production["id"] as JSONArray
            val _id = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("id",getArray(array)))

            array = production["{"] as JSONArray
            val _ob = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("{",getArray(array)))

            array = production["}"] as JSONArray
            val _cb = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("}",getArray(array)))

            array = production[","] as JSONArray
            val _coma = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal(",",getArray(array)))

            array = production["="] as JSONArray
            val _equal = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("=",getArray(array)))

            array = production["val"] as JSONArray
            val _val = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("val",getArray(array)))

            array = production["var"] as JSONArray
            val _var = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("var",getArray(array)))

            array = production["de"] as JSONArray
            val _de = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("de",getArray(array)))

            array = production["dd"] as JSONArray
            val _dd = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("dd",getArray(array)))

            array = production["cs"] as JSONArray
            val _cs = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("cs",getArray(array)))

            array = production["true"] as JSONArray
            val _true = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("true",getArray(array)))

            array = production["false"] as JSONArray
            val _false = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("false",getArray(array)))

            array = production["String"] as JSONArray
            val _String = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("String",getArray(array)))

            array = production["Bool"] as JSONArray
            val _Bool = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("Bool",getArray(array)))

            array = production["Int"] as JSONArray
            val _Int = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("Int",getArray(array)))

            array = production["Float"] as JSONArray
            val _Float = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("Float",getArray(array)))

            array = production["fun"] as JSONArray
            val _fun = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("fun",getArray(array)))

            array = production["("] as JSONArray
            val _op = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("(",getArray(array)))

            array = production[")"] as JSONArray
            val _cp = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal(")",getArray(array)))

            array = production[":"] as JSONArray
            val _colon = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal(":",getArray(array)))

            array = production["return"] as JSONArray
            val _return = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("return",getArray(array)))

            array = production["empty"] as JSONArray
            val _empty = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("empty",getArray(array)))

            array = production["if"] as JSONArray
            val _if = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("if",getArray(array)))

            array = production["else"] as JSONArray
            val _else = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("else",getArray(array)))

            array = production["while"] as JSONArray
            val _while = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("while",getArray(array)))

            array = production["for"] as JSONArray
            val _for = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("for",getArray(array)))

            array = production["until"] as JSONArray
            val _until = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("until",getArray(array)))

            array = production["in"] as JSONArray
            val _in = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("in",getArray(array)))

            array = production["call"] as JSONArray
            val _call = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("call",getArray(array)))

            array = production["+"] as JSONArray
            val _plus = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("+",getArray(array)))

            array = production["-"] as JSONArray
            val _minus = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("-",getArray(array)))

            array = production["*"] as JSONArray
            val _by = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("*",getArray(array)))

            array = production["/"] as JSONArray
            val _div = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("/",getArray(array)))

            array = production["and"] as JSONArray
            val _and = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("and",getArray(array)))

            array = production["or"] as JSONArray
            val _or = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("or",getArray(array)))

            array = production[">"] as JSONArray
            val _gt = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal(">",getArray(array)))

            array = production["<"] as JSONArray
            val _lt = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("<",getArray(array)))

            array = production["!="] as JSONArray
            val _ne = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("!=",getArray(array)))

            array = production["=="] as JSONArray
            val _dequal = getData(array)
            Mutables.syntacticDynamicList[i].add(Terminal("==",getArray(array)))

            Mutables.syntacticTableList.add(SAT(name,_class,_id,_ob,_cb,_coma,_equal,_val,_var,_de,_dd,_cs,
                    _true,_false,_String,_Bool,_Int,_Float,_fun,_op,_cp,_colon,_return,_empty,_if,_else,
                    _while,_for,_until,_in,_call,_plus,_minus,_by,_div,_and,_or,_gt,_lt,_ne,_dequal))
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