package day11

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day11test:StringSpec ({
    "output devices for ccc: ddd eee fff are (ddd, eee, fff)" {
        "ccc: ddd eee fff".outputDevices() shouldBe listOf("ddd", "eee", "fff")
    }
    "routes for sample data is 5" {
        partOne(sampledata) shouldBe 5
    }
    "routes for puzzle input" {
        partOne(puzzleinput) shouldBe 640
    }
    "sample data 2 using routes from part 1 should be 8" {
        sampledata2.routes("svr",end = "fft") shouldBe 1
        sampledata2.routes("fft",end = "dac") shouldBe 1
        sampledata2.routes("dac",end = "out") shouldBe 2

        sampledata2.routes("svr",end = "dac") shouldBe 2
        sampledata2.routes("dac",end = "fft") shouldBe 0
        sampledata2.routes("fft",end = "out") shouldBe 4
    }
    "part two with sample data is 2" {
        partTwo(sampledata2) shouldBe 2
    }
    "part two with puzzle input" {
        partTwo(puzzleinput) shouldBe 367579641755680L
    }
})