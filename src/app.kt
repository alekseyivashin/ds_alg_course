import java.io.File

fun main(args: Array<String>) {
    val a = Reader.lineAsInt(0)
    val b = Reader.lineAsIntList(1)
    val c = Reader.lineAsStringList(2)
    Writer.writeLines(c)
    Writer.writeLines(a)
    Writer.writeLines(b)
    Writer.persist()
}

object Reader {

    private val inputFile: File = File("input.txt")

    private val lines = inputFile.readLines().map { it.trim() }

    fun lineAsString(index: Int) = lines[index]

    fun lineAsInt(index: Int) = lineAsString(index).toInt()

    fun lineAsStringList(index: Int, delimiter: String = " ") = lines[index].split(delimiter).filterNot { it.isBlank() }

    fun lineAsIntList(index: Int, delimiter: String = " ") = lineAsStringList(index, delimiter).map { it.toInt() }

}

object Writer {

    private val outputFile: File = File("output.txt")

    private val linesForWriting = mutableListOf<String>()

    fun <T> writeLines(vararg lines: T) = linesForWriting.addAll(
        lines.map {
            if (it is Collection<*>) it.joinToString(separator = " ") else it.toString()
        }
    )

    fun persist() = outputFile.printWriter().use { out -> linesForWriting.forEach { out.println(it) } }

}
