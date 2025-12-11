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

    "part two with sample data" {
        partTwo(sampledata) shouldBe 24
    }
})