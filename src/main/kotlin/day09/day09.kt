package day09

import java.awt.Polygon
import java.awt.geom.Rectangle2D
import kotlin.collections.toIntArray
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

fun partTwo(input:List<String>):Long {
    val points = parse(input)
    var max = 0L
    points.indices.forEach { i ->
        max = points.biggestArea2(i, max) ?: max
    }
    return max
}

fun poly(points:List<Point>) = Polygon((points.map{it.row.toInt()}.toIntArray()), points.map{it.col.toInt()}.toIntArray(), points.size)

fun rectangle(corner1:Point, corner2:Point) = Rectangle2D.Float().apply {
    setFrame(corner1.row.toDouble(), corner1.col.toDouble(),
        (abs(corner1.row - corner2.row) + 1).toDouble(),(abs(corner1.col - corner2.col) + 1).toDouble()  )
}

fun List<Point>.biggestArea2(index:Int, max:Long):Long? {
    val polygon = poly(this)
    val p1 = Point(2,3)
    val p2 = Point(7,5)
    println("polygon contains rectangle $p1 $p2 ${polygon.contains(rectangle(p1,p2))}")
    return filter { corner1 ->
        val corner2 = get(index)
        val rectangle = rectangle(corner1, corner2)
        if ((corner2.area(corner1) > max) && (polygon.contains(rectangle))) {
            println("polygon contains  ${polygon.contains(rectangle) }")
            println("$corner1, $corner2")
            true
        } else false
    }.maxOfOrNull { get(index).area(it) }
}
