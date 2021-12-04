package day01

import readInputFor

fun main() {
    val input = readInputFor(1, 1)
    val numbers = input.split("\n").map { it.trim().toInt() }
    var previous: Int? = null
    val output = numbers.windowed(3).fold(0) { acc, elements ->
        val sum = elements.sum()
        try {
            if (previous != null && sum > previous!!) {
                acc + 1
            } else {
                acc
            }
        } finally {
            previous = sum
        }
    }
    println(output)
}