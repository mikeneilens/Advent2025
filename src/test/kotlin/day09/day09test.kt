package day09

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day09test:StringSpec( {
    "part one with sample data " {
        partOne(sampledata) shouldBe 50
    }
    "part one with puzzle input " {
        partOne(puzzleInput) shouldBe 4767418746L
    }
    "outer point from first point in sample data should be (12,0)" {
        parse(sampledata).outerPoint(0) shouldBe Point(12,0)
    }
    "outer points for sample data" {
        val points = parse(sampledata)
        points.allOuterPoints() shouldBe listOf(
            Point(col=12, row=0),
            Point(col=12, row=8),
            Point(col=8, row=8),
            Point(col=8, row=6),
            Point(col=1, row=6),
            Point(col=1, row=2),
            Point(col=6, row=2),
            Point(col=6, row=0)
        )
    }
    "outer lines for sample data" {
        val points = parse(sampledata)
        points.allOuterPoints().outerLines() shouldBe listOf(
            Pair(Point(col=12, row=0), Point(col=12, row=8)),
            Pair(Point(col=12, row=8), Point(col=8, row=8)),
            Pair(Point(col=8, row=8), Point(col=8, row=6)),
            Pair(Point(col=8, row=6), Point(col=1, row=6)),
            Pair(Point(col=1, row=6), Point(col=1, row=2)),
            Pair(Point(col=1, row=2), Point(col=6, row=2)),
            Pair(Point(col=6, row=2), Point(col=6, row=0)),
            Pair(Point(col=6, row=0), Point(col=12, row=0))
        )
    }
    "square (2,3)- (11,7) crosses line (1,6) - (8,6)" {
        rectangleCrosses(Point(2,3), Point(11,7), listOf(Pair(Point(1,6), Point(8,6)))) shouldBe true
    }
    "part two with sample data" {
        partTwo(sampledata) shouldBe 24
    }
    "part two with puzzle input" {
        //3083233140 is too high
        //1461954456 is too low
        partTwo(puzzleInput) shouldBe 123
    }
})