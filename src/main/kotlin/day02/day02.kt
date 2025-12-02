package day02

fun partOne(input:String) = input.parse().flatMap { it.invalidNumbersInRange()}.sum()

fun String.parse() = split(",").map{ it.split("-")[0].toLong()..it.split("-")[1].toLong()}

fun LongRange.invalidNumbersInRange() = filter { !it.isValid() }

fun Long.isValid() = toString().isValid()

fun String.isValid() = (length % 2 == 1) || (take(length / 2) != takeLast(length/2))

fun partTwo(input:String) = input.parse().flatMap { it.invalidNumbersInRange2()}.sum()

fun LongRange.invalidNumbersInRange2() = filter { it.containsRepeatingNumbers() }

fun Long.containsRepeatingNumbers() = toString().containsRepeatingString()

fun String.containsRepeatingString() =
    (1..(length/2)).map{chunkSize -> chunked(chunkSize)}.filter{chunks:List<String> -> chunks.all{it == chunks.first()}}.size > 0
