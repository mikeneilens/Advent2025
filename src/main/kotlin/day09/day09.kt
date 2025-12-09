package day09

import kotlin.math.abs

fun partOne(input:List<String>):Long {
    val points = parse(input)
    var max = 0L
    points.indices.forEach { i ->
        max = points.biggestArea(i, max) ?: max
    }
    return max
}

data class Point(val row:Long, val col:Long) {
    fun area(other:Point) = abs((row - other.row + 1L) * (col - other.col + 1L))
}

fun parse(input:List<String>) = input.map{Point(it.split(",")[0].toLong(),it.split(",")[1].toLong())}

fun List<Point>.biggestArea(index:Int, max:Long) = filter { get(index).area(it) > max }.maxOfOrNull { get(index).area(it) }