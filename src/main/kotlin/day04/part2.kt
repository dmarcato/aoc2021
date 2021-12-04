package day04

import inputFor

fun main() {
    val input = inputFor(4, 1).bufferedReader()
    val extractions = input.readLine().split(",").map { it.trim().toInt() }

    val boards = input.readText().split("\n[\\s]*\n".toPattern()).map { elements ->
        Board(elements.trim())
    }

    val (winningBoard, lastExtracted) = extractions.firstNotNullOf { value ->
        val validBoards = boards.filter { !it.won }
        validBoards.forEach { it.mark(value) }
        val stillValid = validBoards.filter { !it.won }
        if (stillValid.isEmpty()) {
            validBoards.last() to value
        } else {
            null
        }
    }

    println(winningBoard.sumUnmarked() * lastExtracted)
}