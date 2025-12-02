package day01

data class Rotation(val qty:Int, val direction: Direction)

enum class Direction{Left, Right}

fun String.toDirection() = if (this == "L") Direction.Left else Direction.Right

data class  Dial(val position:Int, val noOfZeros:Int, val part:String = "one", val dialSize:Int = 100) {

    fun rotate(qty:Int, direction: Direction) = rotators.getValue(direction)(qty).newDial(qty, direction)

    val rotators = mapOf(
        Direction.Right to {qty:Int -> (position + qty) % 100},
        Direction.Left to {qty:Int ->((position + dialSize - qty % 100) % 100)}
    )

    fun Int.newDial(qty:Int, direction: Direction)= copy(position = this, noOfZeros + zeroCount(this, qty,direction))

    fun zeroCount(newPosition:Int, qty:Int, direction: Direction) =
        if (part == "one") calcZeroCountPartOne(newPosition)
        else if (direction == Direction.Right) calcZeroCountPartTwoRight(qty)
        else calcZeroCountPartTwoLeft(qty)

    fun calcZeroCountPartOne(newPosition:Int)  = if (newPosition == 0)  1 else 0

    fun calcZeroCountPartTwoRight(qty:Int) = (position + qty)/dialSize

    fun calcZeroCountPartTwoLeft(qty:Int) = ((if (position > 0) (position - dialSize) else 0) - qty)/-dialSize
}

fun day01(input:List<String>,part:String = "one") =
    input.map(String::toRotation).fold(Dial(50,0,part)) { dial, rotation ->
        dial.rotate(rotation.qty,rotation.direction)
    }

fun String.toRotation() = Rotation(drop(1).toInt(),take(1).toDirection())