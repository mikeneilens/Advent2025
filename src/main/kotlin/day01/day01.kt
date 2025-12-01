package day01

data class Rotation(val qty:Int, val direction: Direction)

enum class Direction{Left, Right}

fun String.toDirection() = if (this == "L") Direction.Left else Direction.Right

data class  Safe(val position:Int, val noOfZeros:Int, val part:String = "one", val dialSize:Int = 100) {

    fun rotate(qty:Int, direction: Direction) = rotators.getValue(direction)(qty).newSafe(qty, direction)

    val rotators = mapOf(
        Direction.Right to {qty:Int -> (position + qty) % 100},
        Direction.Left to {qty:Int ->((position + dialSize - qty % 100) % 100)}
    )

    fun Int.newSafe(qty:Int, direction: Direction)= copy(position = this, noOfZeros + zeroCount(this, qty,direction))

    fun zeroCount(newPosition:Int, qty:Int, direction: Direction) =
        if (part == "one") updateZeroCountPartOne(newPosition)
        else if (direction == Direction.Right) updateZeroCountPartTwoRight(qty)
        else updateZeroCountPartTwoLeft(qty)

    fun updateZeroCountPartOne(newPosition:Int)  = if (newPosition == 0)  1 else 0

    fun updateZeroCountPartTwoRight(qty:Int) = (position + qty)/dialSize

    fun updateZeroCountPartTwoLeft(qty:Int) = ((if (position > 0) (position - dialSize) else 0) - qty)/-dialSize
}

fun day01(input:List<String>,part:String = "one") =
    input.map(String::toRotation).fold(Safe(50,0,part)) { s,rotation ->
        s.rotate(rotation.qty,rotation.direction)
    }

fun String.toRotation() = Rotation(drop(1).toInt(),take(1).toDirection())