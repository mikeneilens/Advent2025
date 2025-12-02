package day02

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day02test:StringSpec ({
    "123 is a valid number" {
        123L.isValid() shouldBe true
    }
    "11 is not a valid number" {
        11L.isValid() shouldBe false
    }
    "123123 is not a valid number" {
        123123L.isValid() shouldBe false
    }
    "invalid numbers in 11..22 are [11,22]" {
        (11L..22L).invalidNumbersInRange() shouldBe listOf(11L,22L)
    }
    "parse sample input" {
        sampleInput.parse() shouldBe listOf(
            11L..22L,
            95L..115L,
            998L..1012L,
            1188511880L..1188511890L,
            222220L..222224L,
            1698522L..1698528L,
            446443L..446449L,
            38593856L..38593862L,
            565653L..565659L,
            824824821L..824824827L,
            2121212118L..2121212124L
        )
    }
    "part one with sample data" {
        partOne(sampleInput) shouldBe 1227775554L
    }
    "part one" {
        partOne(puzzleInput) shouldBe 24043483400L
    }
})

fun Long.isValid() = toString().isValid()

fun String.isValid() = (length % 2 == 1) || (take(length / 2) != takeLast(length/2))
