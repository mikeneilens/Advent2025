package day02

fun partOne(input:String) = input.parse().flatMap (::invalidNumbersInRange).sum()

fun String.parse() = split(",").map{ it.split("-")[0].toLong()..it.split("-")[1].toLong()}

fun invalidNumbersInRange(range: LongRange) = range.filter(Long::containsRepeatedNumber)

fun Long.containsRepeatedNumber() = toString().containsRepeatedString()

fun String.containsRepeatedString() = (length % 2 == 0) && (take(length / 2) == takeLast(length/2))

fun partTwo(input:String) = input.parse().flatMap(::invalidNumbersInRange2).sum()

fun invalidNumbersInRange2(range: LongRange) = range.filter(Long::containsRepeatingNumbers)

fun Long.containsRepeatingNumbers() = toString().containsRepeatingString()

fun String.containsRepeatingString() =
    (1..(length / 2)).map { chunkSize -> chunked(chunkSize) }
        .any { chunks -> chunks.all { it == chunks.first() } }
