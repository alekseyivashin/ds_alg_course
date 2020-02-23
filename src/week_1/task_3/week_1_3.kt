package week_1.task_3

import java.io.File
import java.util.Collections.swap

fun main(args: Array<String>) {
    val n = Reader.lineAsInt(0)
    val a = Reader.lineAsIntList(1)

    val indexes = arrayOfNulls<Int>(n)
    indexes[0] = 1

    for (j in 1 until n) {
        var i = j - 1

        while (i >= 0 && a[i] > a[i + 1]) {
            swap(a, i, i + 1)
            i--
        }

        indexes[j] = i + 2
    }
    Writer.writeLines(indexes, a)
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
