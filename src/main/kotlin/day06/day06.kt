package day06

fun partOne(inputData:List<String>):Long {
    val grid = inputData.toGrid()
    return (0..grid.first().lastIndex).sumOf{ column ->
        grid.map{it[column]}.calculate()
    }
}

fun List<String>.calculate() =
    if (last() == "+") dropLast(1).sumOf { it.toLong() } else dropLast(1).fold(1L) { a,s -> s.toLong() * a }

fun List<String>.toGrid() =
    map{line -> line.removeSpaces().split(" ").filter{it.isNotEmpty()}}

fun String.removeSpaces() = fold(""){output, char -> if (output.isEmpty() || char != ' ' || output.last() != ' ') output + char else output}

fun partTwo(inputData:List<String>) =
    inputData.map{it + ' '}.totals().total

data class Summary(val symbol:Char, val numbers:List<Long>, val total:Long) {
    fun calculate() = if (symbol == '+') numbers.sum() else numbers.fold(1L, Long::times)
}

fun List<String>.totals() = last().foldIndexed(Summary(' ', emptyList(), 0L)) { index, summary, c ->
    val number = this.dropLast(1).numberIn(index)
    if (number == 0L) Summary(' ', emptyList(), summary.total + summary.calculate())
    else Summary( if (c != ' ') c else summary.symbol, summary.numbers + number, summary.total)
}

fun List<String>.numberIn(col:Int) = fold(0L) {total, s -> if (s[col] != ' ') total * 10 + s[col].toString().toLong() else total}