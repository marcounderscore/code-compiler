package files

import models.Register
import java.io.IOException
import java.io.RandomAccessFile

/**
 * Register size: 324 bytes, 64 for every String and 4 for Int
 */
class FileHandler {
    @Throws(IOException::class)
    fun write(register: Register) {
        val file = RandomAccessFile(Constants.file, "rw")
        file.seek(register.position*register.registerSize)

        writeString(register.token,file)
        writeString(register.type,file)
        file.writeInt(register.size)
        writeString(register.value,file)
        writeString(register.category,file)
        writeString(register.typing,file)

        println("Written over symbol_table, Token: "+register.token+" Position: "+register.position+", Category: "+register.category)

        file.close()
    }

    private fun writeString(str: String, file: RandomAccessFile){
        for (i in 0..31) {
            if (str.length>i) file.writeChars(str[i].toString())
            else file.writeChars(' '.toString())
        }
    }

    @Throws(IOException::class)
    fun read(position: Long = 0){
        val file = RandomAccessFile(Constants.file, "rw")
        file.seek(position)
        var position = 0
        while(file.length()>file.filePointer){
            val token = readString(file)
            val type = readString(file)
            val size = file.readInt()
            val value = readString(file)
            val category = readString(file)
            val typing =readString(file)
            if (token[0].toInt()>0){
                /*println("//-----Some register------//")
                println("Token: "+token)
                println("Type: "+type)
                println("Size: "+size)
                println("Value: "+value)
                println("Category: "+category)
                println("Position: "+position)
                println("File pointer: "+file.filePointer)*/
                Mutables.lexerTableList.add(Register(token,type,size,value,category,position.toLong(),typing))
            }
            position++
        }
    }

    private fun readString(file: RandomAccessFile): String {
        var text = ""
        for (i in 0..31) {
            text += file.readChar()
        }
        return text
    }
}