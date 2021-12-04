package day04

import inputFor

class Board(elements: String) {

    private val rows: MutableMap<Int, MutableList<Item>> = mutableMapOf()
    private val cols: MutableMap<Int, MutableList<Item>> = mutableMapOf()
    private val items: MutableMap<Int, Item> = mutableMapOf()

    var won: Boolean = false
        private set

    init {
        elements.split("\n").forEachIndexed { r, row ->
            row.trim().split("\\s+".toPattern()).forEachIndexed { c, element ->
                val item = Item(
                    value = element.trim().toInt(),
                    row = r,
                    col = c
                )
                rows.getOrPut(r) { mutableListOf() }.add(item)
                cols.getOrPut(c) { mutableListOf() }.add(item)
                items[item.value] = item
            }
        }
    }

    fun mark(value: Int): Boolean {
        if (won) {
            return won
        }
        return items[value]?.let { item ->
            item.marked = true
            won = rows[item.row]!!.all { it.marked } || cols[item.col]!!.all { it.marked }
            won
        } ?: false
    }

    fun sumUnmarked(): Int {
        return items.values.filter { !it.marked }.sumOf { it.value }
    }

    override fun toString(): String {
        return buildString {
            rows.keys.sorted().forEach {
                rows[it]!!.forEach { item ->
                    append(item.value)
                    if (item.marked) {
                        append("*")
                    } else {
                        append(" ")
                    }
                    append(" ")
                }
                append("\n")
            }
        }
    }

}

data class Item(
    val value: Int,
    val row: Int,
    val col: Int
) {
    var marked: Boolean = false
}

fun main() {
    val input = inputFor(4, 1).bufferedReader()
    val extractions = input.readLine().split(",").map { it.trim().toInt() }

    val boards = input.readText().split("\n[\\s]*\n".toPattern()).map { elements ->
        Board(elements.trim())
    }

    val (winningBoard, lastExtracted) = extractions.firstNotNullOf { value ->
        boards.firstOrNull {
            it.mark(value)
        }?.let {
            it to value
        }
    }

    println(winningBoard.sumUnmarked() * lastExtracted)
}