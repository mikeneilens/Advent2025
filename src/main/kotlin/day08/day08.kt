package day08

fun partOne(input:List<String>, size:Int = 10):Long {
    val closestToEachPoint = input.toPoints().closestToEach().take(size)
    val circuits = makeCircuits(closestToEachPoint)
    return circuits.map { it.size.toLong() }.sortedDescending().take(3).let { it[0] * it[1] * it[2] }
}

data class Point(val x:Long, val y:Long, val z:Long) {
    operator fun minus(other:Point) = Point(x - other.x, y - other.y, z - other.z)
    val distance get() = (((x * x).toLong() + (y * y).toLong() + (z * z).toLong()))
}

fun List<String>.toPoints() = map{ Point(it.split(",")[0].toLong(),it.split(",")[1].toLong(),it.split(",")[2].toLong()) }

fun makeCircuits(closestPoints:List<Pair<Point, Point>>, circuits:MutableList<MutableSet<Point>> = mutableListOf()):MutableList<MutableSet<Point>> {
    closestPoints.forEach { (p1,p2) -> circuits.connectPoints(p1, p2) }
    return circuits
}

fun MutableList<MutableSet<Point>>.connectPoints(p1:Point, p2:Point) {
    val p1Circuit = find { circuit ->  p1 in circuit }
    val p2Circuit = find { circuit ->  p2 in circuit }
    when {
        p1Circuit != null && p2Circuit != null -> {
            joinCircuits(p1Circuit, p2Circuit)
        }
        p1Circuit == null && p2Circuit == null -> add(mutableSetOf(p1, p2))
        p1Circuit == null -> p2Circuit?.add(p1)
        else -> p1Circuit.add(p2)
    }
}

fun MutableList<MutableSet<Point>>.joinCircuits(c1:Set<Point>, c2:Set<Point>) {
    remove(c1)
    remove(c2)
    add((c1 + c2).toMutableSet())
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


fun partTwo(input:List<String>):Long {
    val closestToEachPoint = input.toPoints().closestToEach()
    return makeCircuits2(closestToEachPoint,input.size)
}

fun makeCircuits2(closestPoints:List<Pair<Point, Point>>, noOfPoints:Int, circuits:MutableList<MutableSet<Point>> = mutableListOf() ):Long {
    closestPoints.forEach { (p1,p2) ->
        circuits.connectPoints(p1, p2)
        if (circuits.any{it.size == noOfPoints}) return p1.x * p2.x
    }
    return 0L
}
