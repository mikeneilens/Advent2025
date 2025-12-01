package day01

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day01test:StringSpec( {
    "rotating dial at position 1 right by 1 leaves it at position 2" {
        Dial(1,0).rotate(1,Direction.Right) shouldBe Dial(2,0)
    }
    "rotating dial at position 1 left by 1 leaves it at position 0" {
        Dial(1,0).rotate(1,Direction.Left) shouldBe Dial(0,1)
    }
    "rotating dial at position 1 right by 100 leaves it at position 1" {
        Dial(1,0).rotate(100,Direction.Right) shouldBe Dial(1,0)
    }
    "rotating dial at position 1 left by 100 leaves it at position 1" {
        Dial(1,0).rotate(100,Direction.Left) shouldBe Dial(1,0)
    }
    "rotating dial at position 1 left by 101 leaves it at position 0" {
        Dial(1,0).rotate(101,Direction.Left) shouldBe Dial(0,1)
    }

    "day 1 with sample data gives Safe(position = 32, noOfZeros = 3)" {
        day01(sampleInput) shouldBe Dial(position = 32, noOfZeros = 3)
    }
    "day 1 with puzzle input shouled be" {
        day01(puzzleInput) shouldBe Dial(position = 90, noOfZeros = 984)
    }
    "part 2 rotating dial 51R from position 50 gives Safe(position = 1, noOfZeros = 1)" {
        Dial(50,0, part = "two").rotate(51,Direction.Right) shouldBe Dial(position = 1, noOfZeros = 1, part = "two")
    }
    "part 2 rotating dial 151R from position 50 gives Safe(position = 1, noOfZeros = 2, part = \"two\")" {
        Dial(50,0, part = "two").rotate(151,Direction.Right) shouldBe Dial(position = 1, noOfZeros = 2, part = "two")
    }
    "part 2 rotating dial 51L from position 50 gives Safe(position = 99, noOfZeros = 1)" {
        Dial(50,0, part = "two").rotate(51,Direction.Left) shouldBe Dial(position = 99, noOfZeros = 1, part = "two")
    }
    "part 2 rotating dial 1000R from position 50 gives Safe(position = 50, noOfZeros = 10)" {
        Dial(50,0, part = "two").rotate(1000,Direction.Right) shouldBe Dial(position = 50, noOfZeros = 10, part = "two")
    }
    "part 2 rotating dial 1000L from position 50 gives Safe(position = 50, noOfZeros = 10)" {
        Dial(50,0, part = "two").rotate(1000,Direction.Left) shouldBe Dial(position = 50, noOfZeros = 10, part = "two")
    }
    "part 2 with sample data gives Safe(position = 32, noOfZeros= 6)" {
        day01(sampleInput, part = "two") shouldBe Dial(position = 32, noOfZeros = 6, part = "two")
    }
    "part 2 with puzzle input shouled be" {
        day01(puzzleInput, part = "two") shouldBe Dial(position = 90, noOfZeros = 5657, part = "two")
    }
})