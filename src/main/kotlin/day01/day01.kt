package day01

data class Rotation(val qty:Int, val direction:String)

data class  Safe(val position:Int, val noOfZeros:Int, val part:String = "one", val dialSize:Int = 100) {
    fun rotate(qty:Int, direction:String) =
        if (direction == "R") rotateRight(qty).newSafe(qty, direction) else rotateLeft(qty).newSafe(qty, direction)

    fun rotateRight(qty:Int) = (position + qty) % 100
    fun rotateLeft(qty:Int) = ((position + dialSize - qty % 100) % 100)

    fun Int.newSafe(qty:Int, direction:String) =
        if (part == "one") Safe(this, if (this == 0) noOfZeros + 1 else noOfZeros)
        else if (direction == "R") Safe(this, noOfZeros + (position + qty)/dialSize,part)
        else Safe(this, noOfZeros + ((if (position > 0) (position - dialSize) else 0) - qty)/-dialSize,part)
}

fun day01(input:List<String>,part:String = "one") =
    input.map(String::toRotation).fold(Safe(50,0,part)) { s,rotation ->
        s.rotate(rotation.qty,rotation.direction)
    }

fun String.toRotation() = Rotation(drop(1).toInt(),take(1))