package day02

import readInputFor

fun main() {
    data class Output(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0)

    val input = readInputFor(2, 1)
    val output = input.split("\n").fold(Output()) { acc, element ->
        val (cmd, amountStr) = element.trim().split(" ")
        val amount = amountStr.toInt()
        when (cmd) {
            "forward" -> acc.copy(
                horizontal = acc.horizontal + amount,
                depth = acc.depth + acc.aim * amount
            )
            "up" -> acc.copy(aim = acc.aim - amount)
            "down" -> acc.copy(aim = acc.aim + amount)
            else -> acc
        }
    }
    println(output.horizontal * output.depth)
}