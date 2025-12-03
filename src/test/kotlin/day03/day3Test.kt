package day03

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day3Test:StringSpec ({
    "max in 987654321111111 is 98" {
        "987654321111111".map{it.digitToInt()}.maxSequence() shouldBe 98
    }
    "max in 811111111111119 is 89" {
        "811111111111119".map{it.digitToInt()}.maxSequence() shouldBe 89
    }
    "total for samnple data is 357" {
        partOne(sampleData) shouldBe 357
    }
    "total for puzzle input is 17376" {
        partOne(puzzleInput) shouldBe 17376L
    }
    "part two size 2 max in 987654321111111 is 98L" {
        "987654321111111".map{it.digitToInt()}.maxSequence(digits = 2) shouldBe 98L
    }
    "part two size 12 max in 987654321111111 is 987654321111L" {
        "987654321111111".map{it.digitToInt()}.maxSequence(digits = 12) shouldBe 987654321111L
    }
    "part two size 12 max in 234234234234278 is 434234234278" {
        "234234234234278".map{it.digitToInt()}.maxSequence(digits = 12) shouldBe 434234234278
    }
    "part two size 12 max in 818181911112111 is 888911112111" {
        "818181911112111".map{it.digitToInt()}.maxSequence(digits = 12) shouldBe 888911112111
    }
    "part two total for sample data is 3121910778619" {
        partTwo(sampleData) shouldBe 3121910778619L
    }
    "part two total for puzzlie input is 172119830406258" {
        partTwo(puzzleInput) shouldBe 172119830406258L
    }
})