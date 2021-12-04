import java.io.InputStream

fun inputFor(day: Int, part: Int, isTest: Boolean = false): InputStream {
    val file = "day${day.toString().padStart(2, '0')}/part$part${if (isTest) "_test" else ""}.txt"
    return Thread.currentThread().contextClassLoader.getResourceAsStream(file)!!
}

fun readInputFor(day: Int, part: Int, isTest: Boolean = false): String {
    return inputFor(day, part, isTest).reader().readText()
}