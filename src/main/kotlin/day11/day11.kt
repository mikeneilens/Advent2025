package day11


fun partOne(input:List<String>) =  input.routes("you")

fun String.outputDevices() = split(": ")[1].split(" ")

fun List<String>.routes(device:String, end:String = "out", memory:MutableMap<String, Long> = mutableMapOf()):Long {
    if (device in memory) return memory.getValue(device)
    if (device == end) return 1
    val outputDevices = firstOrNull() {it.startsWith(device)}?.outputDevices() ?: listOf()
    return (outputDevices.sumOf{ outputDevice ->
        routes(outputDevice, end, memory)
    }).also{memory[device] = it}
}

fun partTwo(input:List<String>) =
    input.routes(device = "svr", end = "fft") * input.routes("fft",end = "dac") * input.routes("dac",end = "out") +
            input.routes(device = "svr", end = "dac") * input.routes("dac",end = "fft") * input.routes("fft",end = "out")


