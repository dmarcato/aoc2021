package day05

import readInputFor
import java.lang.Integer.min
import kotlin.math.max

data class Point(val x: Int, val y: Int) {
    companion object {
        fun from(text: String): Point {
            val (xString, yString) = text.split(",")
            return Point(
                x = xString.trim().toInt(),
                y = yString.trim().toInt()
            )
        }
    }
}

data class Line(val start: Point, val end: Point) {
    companion object {
        fun from(text: String): Line {
            val (startString, endString) = text.split("->")
            return Line(
                start = Point.from(startString),
                end = Point.from(endString)
            )
        }
    }
}

val Line.isHorizontal: Boolean
    get() = start.y == end.y

val Line.isVertical: Boolean
    get() = start.x == end.x

val Line.points: Sequence<Point>
    get() = sequence {
        when {
            isHorizontal -> {
                (min(start.x, end.x) .. max(start.x, end.x)).forEach {
                    yield(Point(it, start.y))
                }
            }
            isVertical -> {
                (min(start.y, end.y) .. max(start.y, end.y)).forEach {
                    yield(Point(start.x, it))
                }
            }
            else -> {
                var p1 = start
                while (p1 != end) {
                    yield(p1)
                    p1 = p1.copy(
                        x = if (start.x > end.x) p1.x - 1 else p1.x + 1,
                        y = if (start.y > end.y) p1.y - 1 else p1.y + 1
                    )
                }
                yield(p1)
            }
        }
    }

class Map {

    private val pointsMap = mutableMapOf<Point, Int>()

    fun addLine(line: Line, allowDiagonal: Boolean) {
        if (allowDiagonal || (line.isVertical || line.isHorizontal)) {
            line.points.forEach {
                pointsMap[it] = pointsMap.getOrElse(it) { 0 } + 1
            }
        }
    }

    fun moreThan2Lines(): Int {
        return pointsMap.values.count { it >= 2 }
    }

    override fun toString(): String {
        return buildString {
            val maxX = pointsMap.keys.maxOf { it.x }
            val maxY = pointsMap.keys.maxOf { it.y }
            (0 .. maxY).forEach { y ->
                (0 .. maxX).forEach { x ->
                    pointsMap[Point(x, y)]?.let { count ->
                        append(count)
                    } ?: append(".")
                }
                append("\n")
            }
        }
    }

}

fun main() {
    val input = readInputFor(5, 1)
    val lines = input.split("\n").map { Line.from(it) }

    // part 1
    val map = Map()
    lines.forEach {
        map.addLine(it, false)
    }
    println("Part 1: ${map.moreThan2Lines()}")

    // part 2
    val map2 = Map()
    lines.forEach {
        map2.addLine(it, true)
    }
    println("Part 2: ${map2.moreThan2Lines()}")
}