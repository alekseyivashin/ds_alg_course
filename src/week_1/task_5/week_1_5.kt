package week_1.task_5

import java.io.File
import java.util.Collections.swap

fun main(args: Array<String>) {
    val n = Reader.lineAsInt(0)
    val a = Reader.lineAsIntList(1)
    val indices = mutableListOf<Pair<Int, Int>>()

    for (j in 0 until n) {
        var min = j
        for (i in j + 1 until n) {
            if (a[i] <= a[min]) {
                min = i
            }
        }
        swap(a, j, min)
        if (j != min) {
            indices.add(j + 1 to min + 1)
        }

    }

    Writer.outputFile.printWriter().use { out ->
        indices.forEach {
            out.println("Swap elements at indices ${it.first} and ${it.second}.")
        }
        out.println("No more swaps needed.")
        out.println(a.joinToString(separator = " "))
    }

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

    val outputFile: File = File("output.txt")

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
