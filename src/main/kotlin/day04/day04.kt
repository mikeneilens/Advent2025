package day04

fun partOne(inputData:List<String>):Int =
    removeRolls(inputData).sumOf { line ->  line.count { it == 'X' } }

fun List<String>.square(point:Point) = this[point.row][point.col]

fun List<String>.surroundingSquares(point:Point) = point.surroundingPoints().map{this.square(it)}

data class Point(val row:Int, val col:Int, val rowRange:IntRange = 0..9, val colRange:IntRange = 0..9) {
    fun surroundingPoints() =
        (-1..1).flatMap{ r -> (-1..1).mapNotNull { c -> if ((c==0 && r==0) || row + r !in rowRange  || col+c !in colRange) null else Point(row + r, col + c ) } }
}

fun partTwo(inputData:List<String>):Int {
    return keepRemovingRolls(inputData).sumOf { line ->  line.count { it == 'X' } }
}

fun keepRemovingRolls(grid:List<String>):List<String> {
    val newGrid = removeRolls(grid)
    return if (newGrid == grid) newGrid else keepRemovingRolls(newGrid)
}

fun removeRolls(grid:List<String>):List<String> {
    val rowRange = 0..grid.lastIndex
    val colRange = 0..grid.first().lastIndex
    return rowRange.map{row ->
        val columns = colRange.mapNotNull{col ->
            if (grid.square(Point(row, col)) == '@' && grid.surroundingSquares(Point(row, col, rowRange, colRange)).count { it =='@' } < 4)
                'X'
            else grid[row][col]
        }
        columns.joinToString("")
    }
}