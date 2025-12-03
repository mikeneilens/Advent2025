package day03

fun partOne(input:List<String>) = input.sumOf{ it.parse().maxSequence(digits = 2)}

fun String.parse() = map{it.digitToInt()}

fun partTwo(input:List<String>) = input.sumOf{ it.parse().maxSequence(digits = 12)}

fun List<Int>.maxSequence(total:Long = 0, digits:Int = 2):Long {
    if (digits == 0) return total
    val max = dropLast(digits - 1).max()
    return drop(indexOf(max) + 1).maxSequence(total * 10L + max, digits -1 )
}
