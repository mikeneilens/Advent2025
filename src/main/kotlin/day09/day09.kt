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

data class Point(val col:Long, val row:Long) {
    operator fun plus(other:Point) = Point(col + other.col, row + other.row)

    fun area(other:Point) = abs((row - other.row + 1L) * (col - other.col + 1L))

    fun direction(other:Point) = when {
        row == other.row && col < other.col -> "Right"
        row == other.row && col > other.col -> "Left"
        col == other.col && row < other.row -> "Down"
        col == other.col && row > other.row -> "Up"
        else -> ""
    }
}

fun parse(input:List<String>) = input.map{Point(it.split(",")[0].toLong(),it.split(",")[1].toLong())}

fun List<Point>.biggestArea(index:Int, max:Long) = filter { get(index).area(it) > max }.maxOfOrNull { get(index).area(it) }

fun partTwo(input:List<String>):Long {
    val points = parse(input)
    val outerLines = points.allOuterPoints().outerLines()
    var max = 0L
    points.indices.forEach { i ->
        max = points.biggestArea3(i, max, outerLines) ?: max
    }
    return max
}


fun List<Point>.biggestArea3(index:Int, max:Long, outerLines:List<Pair<Point, Point>>) = filter { p1 ->
    val p2 = get(index)
    //println("$p1, $p2 ${get(index).area(p1) > max  && !rectangleCrosses(p1,p2, outerLines)}")
    get(index).area(p1) > max  && !rectangleCrosses(p1,p2, outerLines)}
    .maxOfOrNull { get(index).area(it) }

val cornerOffsets = mapOf(
    "RightDown" to Point(+1, -1), "DownRight" to Point(+1, -1),
    "LeftDown" to Point(+1, +1), "DownLeft" to Point(+1, +1),
    "LeftUp" to Point(-1, +1), "UpLeft" to Point(-1, +1),
    "RightUp" to Point(-1, -1), "UpRight" to Point(-1, -1),
)

fun rectangleCrosses(p1:Point, p2:Point, outerLines:List<Pair<Point, Point>>): Boolean {
    return outerLines.any{
        it.first.col == it.second.col && it.first.col in minOf(p1.col,p2.col)..maxOf(p1.col,p2.col) && (p1.row in it.first.row..it.second.row || p2.row in it.first.row..it.second.row) ||
        it.first.row == it.second.row && it.first.row in minOf(p1.row,p2.row)..maxOf(p1.row,p2.row) && (p1.col in it.first.col..it.second.col || p2.col in it.first.col..it.second.col)
}}

fun List<Point>.outerLines() = mapIndexed{ndx, p -> Pair(p, get((ndx + 1) % size)) }
    .map{ (p1,p2) -> ordered(p1, p2) }

fun ordered(p1:Point, p2:Point) = if(p1.row == p2.row && p1.col <= p2.col || p1.col == p2.col && p1.row <= p2.row) Pair(p1,p2) else Pair(p2, p1)

fun List<Point>.allOuterPoints() = indices.map{outerPoint(it)}

fun List<Point>.outerPoint(ndx:Int):Point {
    val cornerType = get(ndx).direction( get((ndx + 1) % size)) + get((ndx + 1) % size).direction( get((ndx + 2) % size))
    return  get((ndx + 1)% size) + cornerOffsets.getValue(cornerType)
}

//