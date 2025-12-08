package day07

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day07test:StringSpec ({
    "point below start is point()" {
        sampledata.pointBelow(sampledata.start()) shouldBe Point(2,7)
    }
    "part two with sampl data is 40" {
        partTwo(sampledata) shouldBe 40L
    }
    "part two with puzzle input is 108924003331749L" {
        partTwo(puzzleinput) shouldBe 108924003331749L
    }
})