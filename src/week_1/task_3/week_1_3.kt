package week_1.task_3

import java.io.File
import java.util.Collections.swap


fun main(args: Array<String>) {
    val n = Reader.lineAsInt(0)
    val array = Reader.lineAsIntList(1)
    val indexArray = arrayOfNulls<Int>(n)
    for (j in 0 until n) {
        var i = j
        while (i > 0 && array[i-1] > array[i]) {
            swap(array, i - 1, i)
            i--
        }
        indexArray[j] = i + 1
    }
    Writer.writeLines(indexArray, array)
    Writer.persist()
}


private object Reader {

    private val inputFile = File("input.txt")

    private val lines = inputFile.readLines().map { it.trim() }

    fun lineAsString(index: Int) = lines[index]

    fun lineAsInt(index: Int) = lineAsString(index).toInt()

    fun lineAsStringList(index: Int, delimiter: String = " ") = lines[index]
        .split(delimiter)
        .filterNot { it.isBlank() }

    fun lineAsIntList(index: Int, delimiter: String = " ") = lineAsStringList(index, delimiter)
        .map { it.toInt() }

}

private object Writer {

    private val outputFile: File = File("output.txt")

    private val linesForWriting = mutableListOf<String>()

    fun <T> writeLines(vararg lines: T) = linesForWriting.addAll(
        lines.map {
            when (it) {
                is Collection<*> -> it.joinToString(separator = " ")
                is Array<*> -> it.joinToString(separator = " ")
                else -> it.toString()
            }
        }
    )

    fun persist() = outputFile.printWriter().use { out ->
        linesForWriting.forEach { out.println(it) }
    }

}
