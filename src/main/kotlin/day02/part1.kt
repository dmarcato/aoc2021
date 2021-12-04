package day02

import readInputFor

fun main() {
    data class Output(val horizontal: Int = 0, val depth: Int = 0)

    val input = readInputFor(2, 1)
    val output = input.split("\n").fold(Output()) { acc, element ->
        val (cmd, amountStr) = element.trim().split(" ")
        val amount = amountStr.toInt()
        when (cmd) {
            "forward" -> acc.copy(horizontal = acc.horizontal + amount)
            "up" -> acc.copy(depth = acc.depth - amount)
            "down" -> acc.copy(depth = acc.depth + amount)
            else -> acc
        }
    }
    println(output.horizontal * output.depth)
}