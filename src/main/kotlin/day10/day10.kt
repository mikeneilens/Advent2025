package day10

fun partOne(input:List<String>):Int {
    return input.sumOf{
        val lights = it.lights()
        val buttons = it.buttons()
        buttonSequence(lights, buttons).size
    }
}

fun String.lights() = split(" ")[0].drop(1).dropLast(1).toList()

fun String.buttons() = split(" ").drop(1).dropLast(1).map{ it.drop(1).dropLast(1).split(",").map{it.toString().toInt()}}

fun List<Char>.toggle(buttons:List<Int>) = buttons.fold(this){l, b -> l.toggle(b)}

fun List<Char>.toggle(button:Int) = mapIndexed { i, c -> if(i == button) if (c == '.') '#' else '.' else c }

fun <T>List<T>.combinations(output:List<List<T>> = listOf(listOf())):List<List<T>> {
    if (isEmpty()) return output.drop(1)
    else {
        val n = first()
        val newOutput = output + output.map {it + n}
        return drop(1).combinations(newOutput)
    }
}

fun buttonSequence(light:List<Char>, buttons:List<List<Int>>):List<List<Int>> {
    val combinations = buttons.combinations()
    return combinations.filter{ it.fold(".".repeat(light.size).toList()){ lights, buttons -> lights.toggle(buttons)} == light }
        .minBy { it.size }
}

fun partTwo(input:List<String>):Int {
    return input.sumOf{
        val counters = it.counters()
        val buttons = it.buttons()
        println(counters)
        findTarget(counters, buttons)
    }
}

fun findTarget(target:List<Int>, buttons:List<List<Int>>, number:List<Int> = List(target.size){0}, size:Int= 0):Int {
    if (target == number) {  return size }
    //if (current > target.lastIndex) {return Int.MAX_VALUE }
    else {
        val digitsToSolve = target.indices.filter{target[it] > number[it]}
        val current = digitsToSolve.minBy{ buttons.count{button -> it in button } }
        val buttonForCurrentDigit =  buttons.filter{it.any{it == current}}
        val combinationOfButtons: List<List<List<Int>>> = combinationsN(buttonForCurrentDigit, target[current] - number[current])
        val validCombinations = combinationOfButtons.filter { buttonsToTry -> number.add(buttonsToTry.flatten()).toInt() <= target.toInt()}
        return validCombinations.minOfOrNull { buttonsToTry ->
            findTarget(target, buttons,number.add(buttonsToTry.flatten()), size + buttonsToTry.size )
        } ?: Int.MAX_VALUE
    }
}

fun <T>combinationsN(chars:List<T>, n: Int, output:List<List<T>> = listOf(listOf()), depth:Int = chars.lastIndex):List<List<T>> {
    if (depth < 0) return output.filter{it.size == n}
    else return combinationsN(chars,n, (0..(n)).flatMap{ i -> output.map{ it +  List(i){chars[depth]}}.filter{it.size <= n}}, depth -1)
}

fun List<Int>.add(buttons:List<Int>) =
    mapIndexed { i, n -> n + buttons.count{it == i}}

fun String.counters() = split(" ").last().drop(1).dropLast(1).split(",").map{it.toString().toInt()}

fun List<Int>.increaseCount(button:List<Int>) = mapIndexed { index, n -> if(index in button) n + 1 else n }

fun List<Int>.toInt() = fold(0){a,n -> a * 10 + n}
