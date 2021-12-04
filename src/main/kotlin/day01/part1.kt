package day01

import readInputFor

fun main() {
    val input = readInputFor(1, 1)
    var previous: Int? = null
    val output = input.split("\n").fold(0) { acc, element ->
        val elementInt = element.trim().toInt()
        try {
            if (previous != null && elementInt > previous!!) {
                acc + 1
            } else {
                acc
            }
        } finally {
            previous = elementInt
        }
    }
    println(output)
}