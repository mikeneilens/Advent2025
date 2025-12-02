package day02

fun partOne(input:String) = input.parse().flatMap { it.invalidNumbersInRange()}.sum()

fun String.parse() = split(",").map{ it.split("-")[0].toLong()..it.split("-")[1].toLong()}

fun LongRange.invalidNumbersInRange() = filter { !it.isValid() }

fun Long.isValid() = toString().isValid()

fun String.isValid() = (length % 2 == 1) || (take(length / 2) != takeLast(length/2))