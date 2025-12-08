package day05

fun partOne(inputData:String):Int {
    val (freshRanges, ingredients) = parse(inputData)
    return freshIngredients(freshRanges, ingredients).size
}

fun freshIngredients(freshRanges:List<LongRange>, ingredients:List<Long>) =
    ingredients.filter{ ingredient -> freshRanges.any{ingredient in it}}

fun parse(inputData:String) = Pair(
    inputData.split("\n\n")[0].split("\n").map{it.split("-")[0].toLong()..it.split("-")[1].toLong()},
    inputData.split("\n\n")[1].split("\n").map(String::toLong)
)

fun partTwo(inputData: String):Long {
    val (freshRanges, _) = parse(inputData)
    val normalisedRanges = freshRanges.sortedBy { it.first }.normalise()
    return normalisedRanges.sumOf { it.last - it.first + 1L }
}

fun List<LongRange>.normalise(output:List<LongRange> = emptyList()):List<LongRange> {
    if (isEmpty()) return output
    val next = if (output.isEmpty()) listOf(first()) else output.last() + first()
    return drop(1).normalise(output.dropLast(1) + next)
}

operator fun LongRange.plus(other:LongRange) = add(this, other)

fun add(r1:LongRange, r2:LongRange) = when {
    r1.first <= r2.first && r1.last >= r2.last -> listOf(r1)
    r1.first == r2.first -> listOf(r2)
    r1.first < r2.first && r1.last >= r2.first -> listOf(r1.first..r2.last)
    else -> listOf(r1, r2)
}
