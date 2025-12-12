package day12

fun partOne(input:List<String>):Int {
    val regions = input.getRegions()
    val presents = input.getPresentSizes()
    return regions.count { region -> region.regionCouldContain(presents) }
}

fun List<String>.getPresentSizes() = dropLast(1).associate { it.split("\n").getPresentSize() }

fun List<String>.getRegions() = last().split("\n").map{it.getRegion()}

fun List<String>.getPresentSize() = Pair(
        first().first(),
        drop(1).sumOf{ line -> line.count{ it == '#' }}
    )

fun String.getRegion() = Pair(
    split(":").first().split("x").fold(1){a,r -> a * r.toInt()},
    split(": ").last().split(" ").map(String::toInt)
)

fun Pair<Int, List<Int>>.regionCouldContain(presents:Map<Char, Int>) =
    second.mapIndexed { i, q -> presents.getValue(i.toString()[0]) * q}.sum() <= first
