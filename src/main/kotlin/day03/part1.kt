package day03

import readInputFor

fun main() {
    data class BitInfo(val ones: Int = 0, val zeros: Int = 0) {
        val maxValue: Int
            get() = if (ones > zeros) 1 else 0
        val minValue: Int
            get() = if (ones < zeros) 1 else 0
    }

    val input = readInputFor(3, 1)
    var output: Array<BitInfo> = Array(0) { BitInfo() }
    input.split("\n").forEach { fullElement ->
        val element = fullElement.trim()
        if (output.isEmpty()) {
            output = Array(element.length) { BitInfo() }
        }
        element.forEachIndexed { index, bit ->
            val bitInfo = output[index]
            output[index] = when (bit) {
                '0' -> bitInfo.copy(zeros = bitInfo.zeros + 1)
                '1' -> bitInfo.copy(ones = bitInfo.ones + 1)
                else -> bitInfo
            }
        }
    }
    val gamma = output.fold("") { acc, element -> acc + element.maxValue.toString() }
    val epsilon = output.fold("") { acc, element -> acc + element.minValue.toString() }
    println(gamma.toInt(2) * epsilon.toInt(2))
}