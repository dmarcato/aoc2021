package day06

import readInputFor

fun countPopulation(daysLeft: Int, instances: Long, daysRemaining: Int): Long {
    val remaining = (daysRemaining - daysLeft).coerceAtLeast(0)
    val count = remaining / 7 + if (remaining.mod(7) > 0) 1 else 0
    return instances + (0 until count).sumOf { i ->
        countPopulation(8, instances, remaining - (7 * i) - 1)
    }
}

fun observeFor(fishes: Map<Int, Long>, days: Int) {
    var index = 0
    println("Observing for $days days...")
    val population = fishes.entries.sumOf { (daysLeft, instances) ->
        println("Counting group with $daysLeft days left ($instances instances)... [${index++/fishes.size.toFloat() * 100}%]")
        countPopulation(daysLeft, instances, days)
    }
    println("After $days days: $population")
}

fun main() {
    val input = readInputFor(6, 1)
    val fishes = input.split(",")
        .groupBy { it.trim().toInt() }
        .mapValues { it.value.size.toLong() }

    observeFor(fishes, 80)
    observeFor(fishes, 256)
}