package week_1.task_5

import java.io.File
import java.util.Collections.swap

fun main(args: Array<String>) {
    val n = Reader.lineAsInt(0)
    val a = Reader.lineAsIntList(1)
    val indices = mutableListOf<Pair<Int, Int>>()
//    val jArray = mutableListOf<Int>()

    for (j in 1 until n) {
        var i = j - 1

        while (i >= 0 && a[i] > a[i + 1]) {
            swap(a, i, i + 1)
//            if (indices.isNotEmpty() && indices.last().first == i + 2 && j != jArray.last()) {
//                val second = indices.last().second
//                indices.removeAt(indices.lastIndex)
//                indices.add(i + 1 to second)
//                jArray.add(j)
//            } else {
//                indices.add(i + 1 to i + 2)
//                jArray.add(j)
//            }
            indices.add(i + 1 to i + 2)

            i--
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
