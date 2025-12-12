package day12

import day02.partOne
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day12test:StringSpec ({
    "convert first item of sampleData into a present" {
        val (number, size) = sampledata.first().split("\n").getPresentSize()
        number shouldBe '0'
        size shouldBe 7
    }
    "convert first region in sample data" {
        val (size, presentsRequired) = sampledata.last().split("\n").first().getRegion()
        size shouldBe 16
        presentsRequired shouldBe listOf(0, 0, 0, 0, 2, 0)
    }
    "get presents in sample data" {
        sampledata.getPresentSizes() shouldBe mapOf(
            '0' to 7 , '1' to 7, '2' to 7, '3' to 7, '4' to 7, '5' to 7
        )
    }
    "get regions in sample data" {
        sampledata.getRegions() shouldBe listOf(
            Pair(16, listOf(0, 0, 0, 0, 2, 0)),
            Pair(60, listOf(1, 0, 1, 0, 2, 2)),
            Pair(60, listOf(1, 0, 1, 0, 3, 2))
        )
    }
    "presents fit in region 0" {
        val region = sampledata.getRegions()[0]
        val presents = sampledata.getPresentSizes()
        region.regionCouldContain(presents) shouldBe true
    }
    "presents dont fit in region 2" {
        val region = sampledata.getRegions()[2]
        val presents = sampledata.getPresentSizes()
        region.regionCouldContain(presents) shouldBe true
    }
    "part one with puzzle input" {
        partOne(puzzleinput) shouldBe 448
    }
})