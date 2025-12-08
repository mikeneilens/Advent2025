package day04

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day04test: StringSpec ({
    "points surrounding point (5,5) are (4,4),(4,5),(4,6), (5,4),(5,6),(6,4),(6,5),(6,6) " {
        Point(row=5,col=5).surroundingPoints().toSet() shouldBe listOf(
            Point(4,4),
            Point(4,5),
            Point(4,6),
            Point(5,4),
            Point(5,6),
            Point(6,4),
            Point(6,5),
            Point(6,6),
        ).toSet()
    }
    "points surrounding point(0,5) are (0,4) (0,6), (1,4),(1,5),(1,6)" {
        Point(row=0,col=5).surroundingPoints().toSet() shouldBe listOf(
            Point(0,4),
            Point(0,6),
            Point(1,4),
            Point(1,5),
            Point(1,6),
        ).toSet()
    }
    "points surrounding point(9,5) are (9,4) (9,6), (8,4),(8,5),(8,6)" {
        Point(row = 9, col = 5).surroundingPoints().toSet() shouldBe listOf(
            Point(9, 4),
            Point(9, 6),
            Point(8, 4),
            Point(8, 5),
            Point(8, 6),
        ).toSet()
    }
    "sample data has 13 rolls of paper accessible" {
        partOne(sampledata) shouldBe 13
    }
    "puzzle data has rolls of paper accessible" {
        partOne(puzzleinput) shouldBe 1449
    }
    "delete all rolls from sample data once" {
        removeRolls(sampledata) shouldBe listOf(
            "..XX.XX@X.",
            "X@@.@.@.@@",
            "@@@@@.X.@@",
            "@.@@@@..@.",
            "X@.@@@@.@X",
            ".@@@@@@@.@",
            ".@.@.@.@@@",
            "X.@@@.@@@@",
            ".@@@@@@@@.",
            "X.X.@@@.X."
        )
    }
    "keep removing rolls from sample data" {
        keepRemovingRolls(sampledata) shouldBe listOf(
            "..XX.XXXX.",
            "XXX.X.X.XX",
            "XXXXX.X.XX",
            "X.XX@@..X.",
            "XX.@@@@.XX",
            ".XX@@@@@.X",
            ".X.@.@.@@X",
            "X.X@@.@@@X",
            ".XX@@@@@X.",
            "X.X.@@@.X."
        )
    }
    "part two with sample data is 43" {
        partTwo(sampledata) shouldBe 43
    }
    "part two with puzzleInput is 8746" {
        partTwo(puzzleinput) shouldBe 8746
    }
})