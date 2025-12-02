package day02

fun partOne(input:String) = input.parse().flatMap { it.invalidNumbersInRange()}.sum()

fun String.parse() = split(",").map{ it.split("-")[0].toLong()..it.split("-")[1].toLong()}

fun LongRange.invalidNumbersInRange() = filter { it.containsRepeatedNumber() }

fun Long.containsRepeatedNumber() = toString().containsRepeatedString()

fun String.containsRepeatedString() = (length % 2 == 0) && (take(length / 2) == takeLast(length/2))

fun partTwo(input:String) = input.parse().flatMap { it.invalidNumbersInRange2()}.sum()

fun LongRange.invalidNumbersInRange2() = filter { it.containsRepeatingNumbers() }

fun Long.containsRepeatingNumbers() = toString().containsRepeatingString()

fun String.containsRepeatingString() =
    (1..(length/2)).map{chunkSize -> chunked(chunkSize)}.filter{chunks:List<String> -> chunks.all{it == chunks.first()}}.size > 0
