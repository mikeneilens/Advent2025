package day11

fun partOne(input:List<String>) =  input.routes("you")

fun String.outputDevices() = split(": ")[1].split(" ")

fun List<String>.outputsFor(device:String) = firstOrNull{it.startsWith(device)}?.outputDevices() ?: listOf()

fun List<String>.routes(device:String, end:String = "out", memory:MutableMap<String, Long> = mutableMapOf()):Long =when {
        (device in memory) -> memory.getValue(device)
        (device == end) -> 1L
        else -> (outputsFor(device).sumOf{ outputDevice -> routes(outputDevice, end, memory) }).also{memory[device] = it}
    }

fun partTwo(input:List<String>) =
    input.routes(device = "svr", end = "fft") * input.routes("fft",end = "dac") * input.routes("dac",end = "out") +
            input.routes(device = "svr", end = "dac") * input.routes("dac",end = "fft") * input.routes("fft",end = "out")


