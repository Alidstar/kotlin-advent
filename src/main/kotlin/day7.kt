import kotlin.math.abs

fun fuelUsedAt(data: List<Int>, index: Int): Int {
    return data.fold(0) { acc, position -> acc + abs(position - index) }
}

fun fuelUsedAt2(data: List<Int>, index: Int): Int {
    return data.fold(0) { acc, position -> acc + (1 + abs(position - index)) * abs(position - index) / 2 }
}

fun main() {
    val data = {}::class.java.getResource("day7.txt")!!.readText()
    val crabPositions = data.split(",").map { it.toInt() }
    val maxPosition = crabPositions.max()
    val minPosition = crabPositions.min()

    var lastFuelUsed = fuelUsedAt(crabPositions, 0)
    for (i in minPosition + 1..maxPosition) {
        val thisFuelUsed = fuelUsedAt(crabPositions, i)
        if (thisFuelUsed > lastFuelUsed) break
        lastFuelUsed = thisFuelUsed
    }
    println("min fuel use = $lastFuelUsed")

    lastFuelUsed = fuelUsedAt2(crabPositions, 0)
    for (i in minPosition + 1..maxPosition) {
        val thisFuelUsed = fuelUsedAt2(crabPositions, i)
        if (thisFuelUsed > lastFuelUsed) break
        lastFuelUsed = thisFuelUsed
    }
    println("min fuel use 2 = $lastFuelUsed")
}