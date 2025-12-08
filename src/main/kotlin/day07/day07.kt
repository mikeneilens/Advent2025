package day07

data class Point(val row:Int, val col:Int) {
    fun belowLeft() = Point(row + 1, col -1)
    fun belowRight() = Point(row + 1, col + 1)
}

fun partTwo(input:List<String>):Long {
    return input.routes(input.start())
}

fun List<String>.routes(point:Point, memory:MutableMap<Point, Long> = mutableMapOf()):Long {
    if (point in memory) return memory.getValue(point)
    val splitter = pointBelow(point)
    return (if (splitter != null) {
        routes(splitter.belowLeft(), memory) + routes(splitter.belowRight(), memory)
    } else 1)
        .also { memory[point] = it  }
}

fun List<String>.start() = Point(0,first().indexOf('S'))

fun List<String>.pointBelow(point:Point):Point? =
    ((point.row + 1)..lastIndex).firstOrNull{ this[it][point.col] == '^' }?.let{Point(it, point.col)}

