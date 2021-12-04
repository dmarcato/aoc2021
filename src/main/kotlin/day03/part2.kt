package day03

import readInputFor

private data class BitInfo(val ones: Int = 0, val zeros: Int = 0) {
    val maxValue: Int
        get() = if (ones >= zeros) 1 else 0
    val minValue: Int
        get() = if (ones < zeros) 1 else 0
}

fun main() {
    val input = readInputFor(3, 1)
    val elements = input.split("\n").map { it.trim() }
    var oxygen = elements
    var co2 = elements

    var index = 0
    while (oxygen.size > 1) {
        val bitInfo = bitInfoOf(oxygen, index)
        oxygen = oxygen.filter {
            it[index].digitToInt() == bitInfo.maxValue
        }
        index++
    }
    index = 0
    while (co2.size > 1) {
        val bitInfo = bitInfoOf(co2, index)
        co2 = co2.filter {
            it[index].digitToInt() == bitInfo.minValue
        }
        index++
    }

    println(oxygen.first().toInt(2) * co2.first().toInt(2))
}

private fun bitInfoOf(elements: List<String>, index: Int): BitInfo {
    return elements.fold(BitInfo()) { acc, element ->
        when (element[index]) {
            '0' -> acc.copy(zeros = acc.zeros + 1)
            '1' -> acc.copy(ones = acc.ones + 1)
            else -> acc
        }
    }
}