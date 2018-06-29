package files

import models.Register
import java.io.IOException
import java.io.RandomAccessFile

/**
 * Register size: 260 bytes, 64 for every String and 4 for Int
 */
class FileHandler {
    @Throws(IOException::class)
    fun write(register: Register) {
        val file = RandomAccessFile("symbol_table", "rw")
        file.seek(register.position*register.registerSize)

        writeString(register.token,file)
        writeString(register.type,file)
        file.writeInt(register.size)
        writeString(register.value,file)
        writeString(register.category,file)

        println("Written over symbol_table")

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
        val file = RandomAccessFile("symbol_table", "rw")
        file.seek(position)
        var position = 0
        do {
            println("//-----Some register------//")
            val token = readString(file)
            println("Token: "+token)
            val type = readString(file)
            println("Type: "+type)
            val size = file.readInt()
            println("Size: "+size)
            val value = readString(file)
            println("Value: "+value)
            val category = readString(file)
            println("Category: "+category)
            position++
            println("Position: "+position)
            println("File pointer: "+file.filePointer)
        }while (file.length()>file.filePointer)
    }

    private fun readString(file: RandomAccessFile): String {
        var text = ""
        for (i in 0..31) {
            text += file.readChar()
        }
        return text
    }
}