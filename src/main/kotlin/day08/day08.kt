package day08

import kotlin.math.sqrt

fun partOne(input:List<String>, size:Int = 10):Long {
    val closestToEachPoint = input.toPoints().closestToEach().take(size)
    val circuits = makeCircuits(closestToEachPoint)
    return circuits.sortedByDescending{it.size}.take(3).fold(1L) { a, j -> a * j.size }
}

data class Point(val x:Int, val y:Int, val z:Int) {
    operator fun minus(other:Point) = Point(x - other.x, y - other.y, z - other.z)
    val distance: Double get() = sqrt(((x * x) + (y * y) + (z * z)).toDouble())
}

fun List<String>.toPoints() = map{ Point(it.split(",")[0].toInt(),it.split(",")[1].toInt(),it.split(",")[2].toInt()) }

fun makeCircuits(closestPoints:List<Pair<Point, Point>>, circuits:MutableList<MutableSet<Point>> = mutableListOf()):MutableList<MutableSet<Point>> {
    closestPoints.forEach { (p1,p2) ->
        val p1Circuits = circuits.find { circuit ->  p1 in circuit }
        val p2Circuits = circuits.find { circuit ->  p2 in circuit }
        when {
            p1Circuits == null && p2Circuits == null -> circuits.add(mutableSetOf(p1, p2))
            p1Circuits == null -> p2Circuits?.add(p1)
            p2Circuits == null -> p1Circuits?.add(p2)
            else -> {
                circuits.remove(p1Circuits)
                circuits.remove(p2Circuits)
                circuits.add((p1Circuits + p2Circuits).toMutableSet())
            }
        }
    }
    return circuits
}

fun List<Point>.closestToEach() = getPairs().map { Pair(it, distanceBetween(it.first, it.second))}
    .sortedBy { it.second }.map{it.first}

fun List<Point>.getPairs(): List<Pair<Point, Point>> {
    val output = mutableListOf<Pair<Point, Point>>()
    forEachIndexed { i, first ->
        drop(i + 1).forEach { second ->
            output.add(Pair(first, second))
        }
    }
    return output
}

fun distanceBetween(point1:Point, point2:Point) =  ( (point1 - point2).distance)