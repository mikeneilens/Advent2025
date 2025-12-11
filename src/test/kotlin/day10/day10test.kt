package day10

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day10test:StringSpec ({
    "parse [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7} into lights and buttons" {
        "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}".lights() shouldBe ".##.".toList()
        "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}".buttons() shouldBe listOf(
            listOf(3), listOf(1, 3), listOf(2), listOf(2, 3), listOf(0, 2), listOf(0, 1)
        )
    }
    "toggle .##. using button 3" {
        ".##.".toList().toggle(3) shouldBe ".###".toList()
        ".###".toList().toggle(3) shouldBe ".##.".toList()
    }
    "toggle .##. using buttons (1,3)" {
        ".##.".toList().toggle(listOf(1,3)) shouldBe "..##".toList()
    }
    "combinations of (1,2) is ((1),(2),(1,2))" {
        listOf(1,2).combinations() shouldBe listOf(listOf(1), listOf(2), listOf(1,2))
    }
    "turn on lights using [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1)" {
        val light = ".##.".toList()
        val buttons = listOf(listOf(3), listOf(1, 3), listOf(2), listOf(2, 3), listOf(0, 2), listOf(0, 1))
        buttonSequence(light, buttons).size shouldBe 2
    }
    "part one sample data" {
        partOne(sampledata) shouldBe 7
    }
    "part one puzzle input" {
        partOne(puzzleinput) shouldBe 502
    }
    "parse [.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7} into counters" {
        "[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}".counters() shouldBe listOf(3, 5, 4, 7)
    }
    "increasing count of  (3,5,4,7) using button (1,3) gives (3,6,4,8)" {
        listOf(3,5,4,7).increaseCount(listOf(1,3)) shouldBe listOf(3,6,4,8)
    }
    "find best sequence for (3) (1,3) (2) (2,3) (0,2) (0,1) and {3,5,4,7}" {
        val target = listOf(3,5,4,7)
        val buttons = listOf(listOf(3), listOf(1, 3), listOf(2), listOf(2, 3), listOf(0, 2), listOf(0, 1))
         findTarget(target, buttons) shouldBe 10
    }
    "combinations of length 3" {
        combinationsN(listOf('a','b'),3) shouldBe listOf(
            listOf('b','b','b'),
            listOf('b','b','a'),
            listOf('b','a','a'),
            listOf('a','a','a')
        )
    }
    "add (0,2) to 1234" {
        listOf(1,2,3,4).add(listOf(0,2)) shouldBe listOf(2,2,4,4)
    }
    "add (2,2) to 1234" {
        listOf(1,2,3,4).add(listOf(2,2)) shouldBe listOf(1,2,5,4)
    }
    "part two with sample data" {
        partTwo(sampledata) shouldBe 33
    }
    "part two with puzzle input" {
        partTwo(puzzleinput) shouldBe 33
    }
})

