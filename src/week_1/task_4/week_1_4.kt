package week_1.task_4

import java.io.File
import java.util.Collections.swap

fun main(args: Array<String>) {
    val n = Reader.lineAsInt(0)
    val a = Reader.lineAsDoubleList(1)

    val citizens = Array(n) { it + 1 }.toList()

    for (j in 1 until n) {
        var i = j - 1

        while (i >= 0 && a[i] > a[i + 1]) {
            swap(a, i, i + 1)
            swap(citizens, i, i + 1)
            i--
        }
    }

    val midIndex = n / 2
    Writer.writeToLine(citizens.first(), citizens[midIndex], citizens.last())
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

    fun lineAsDoubleList(index: Int, delimiter: String = " ") = lineAsStringList(index, delimiter)
        .map { it.toDouble() }

}

private object Writer {

    private val outputFile: File = File("output.txt")

    private val linesForWriting = mutableListOf<String>()

    fun <T> writeToLine(vararg values: T) = linesForWriting.add(
        values.joinToString(separator = " ")
    )

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
