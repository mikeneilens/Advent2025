package day05

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day05test: StringSpec( {
    "parse input data into ranges and ingredients" {
        val (ranges, ingredients) = parse(sampledata)
        ranges shouldBe listOf(
            3L..5L,10L..14L,16L..20L,12L..18L
        )
        ingredients shouldBe listOf(
            1L, 5L, 8L, 11L, 17L, 32L
        )
    }
    "part one with sample data should be 3" {
        partOne(sampledata) shouldBe 3
    }
    "part one with puzzle input should be " {
        partOne(puzzleInput) shouldBe 520
    }
    "sample data ranges normalised" {
        parse(sampledata).first.sortedBy { it.first }.normalise() shouldBe listOf(
            3L..5L,
            10L..20L
        )
    }
    "1..5 + 1..5 should be 1..5" {
        (1L..5L) + (1L..5L) shouldBe listOf(1L..5L)
    }
    "1..5 + 1..5 should be 1..10" {
        (1L..5L) + (1L..10L) shouldBe listOf(1L..10L)
    }
    "1..5 + 1..10 should be 1..5" {
        (1L..10L) + (1L..5L) shouldBe listOf(1L..10L)
    }
    "1..10 + 5..10 should be 1..10" {
        (1L..10L) + (5L..10L) shouldBe listOf(1L..10L)
    }
    "1..10 + 5..7 should be 1..10" {
        (1L..10L) + (5L..7L) shouldBe listOf(1L..10L)
    }
    "1..5 + 5..10 should be 1..10" {
        (1L..5L) + (5L..10L) shouldBe listOf(1L..10L)
    }
    "1..4 + 5..10 should be 1..4, 5..10" {
        (1L..4L) + (5L..10L) shouldBe listOf(1L..4L, 5L..10L)
    }
    "part two with sample data is 14" {
        partTwo(sampledata) shouldBe 14L
    }
    "part two with puzzle input is 14" {
        partTwo(puzzleInput) shouldBe 347338785050515L
    }
})