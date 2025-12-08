package day08

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class day08test:StringSpec ({
    "closest to each point" {
        val closestToEachPoint = sampledata.toPoints().closestToEach()
        closestToEachPoint[0] shouldBe setOf(Point(162,817,812), Point(425,690,689))
        closestToEachPoint[1] shouldBe setOf(Point(x=162, y=817, z=812), Point(x=431, y=825, z=988))
        closestToEachPoint[2] shouldBe setOf(Point(x=906, y=360, z=560), Point(x=805, y=96, z=715))
        closestToEachPoint[3] shouldBe setOf(Point(x=431, y=825, z=988 ), Point(x=425,y=690,z=689))
        closestToEachPoint[4] shouldBe setOf(Point(x=862, y=61, z=35), Point(x=984, y=92, z=344))
        closestToEachPoint[5] shouldBe setOf(Point(x=52, y=470, z=668), Point(x=117, y=168, z=530))
        closestToEachPoint[6] shouldBe setOf(Point(x=819, y=987, z=18), Point(x=941, y=993, z=340))
        closestToEachPoint[7] shouldBe setOf(Point(x=906, y=360, z=560), Point(x=739, y=650, z=466))
        closestToEachPoint[8] shouldBe setOf(Point(x=346, y=949, z=466), Point(x=425, y=690, z=689))
        closestToEachPoint[9] shouldBe setOf(Point(x=906, y=360, z=560), Point(x=984, y=92, z=344))
    }
    "connect all points" {
        val closestToEachPoint = sampledata.toPoints().closestToEach().take(10)
        val circuits = makeCircuits(closestToEachPoint)
        circuits.sortedByDescending{it.size}.take(3).fold(1) { a, j -> a * j.size } shouldBe 40
    }
    "part one with sample data" {
        partOne(sampledata, size = 10) shouldBe 40L
    }
    "part one with puzzle input" {
        partOne(puzzleinput, size = 1000) shouldBe 103488
    }
    "part two with sample data" {
        partTwo(sampledata) shouldBe 25272L
    }
    "part two with puzzleinput" {
        partTwo(puzzleinput) shouldBe 8759985540L
    }
})