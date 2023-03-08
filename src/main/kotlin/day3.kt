fun findPower(inputs: List<String>): Pair<Int, Int> {
    val bitLength = inputs[0].length
    val zeros = IntArray(bitLength) { 0 }
    inputs.forEach {
        val intValue = Integer.parseInt(it, 2)
        for (i in 0 until bitLength) {
            if ((intValue and (1 shl i)) == 0) zeros[i]++
        }
    }

    val gamma = zeros.foldIndexed(0) { i, acc, count -> acc + if (count > inputs.size / 2) 0 else 1 shl i }
    val epsilon = gamma xor ((1 shl bitLength) - 1)
    return Pair(gamma, epsilon)
}

fun getLifeSupportRating(inputs: List<String>, majority: Boolean): Int {
    val bitLength = inputs[0].length
    var inputsCopy = inputs.map { Integer.parseInt(it, 2) }
    for (i in bitLength - 1 downTo 0) {
        val zeroCount = inputsCopy.count { (it and (1 shl i)) == 0 }
        val filterZero = (zeroCount > inputsCopy.size / 2) xor !majority
        inputsCopy = inputsCopy.filter { filterZero xor ((it and (1 shl i)) != 0) }
        if (inputsCopy.size == 1) break
    }
    return inputsCopy[0]
}

fun main() {
    val data = {}::class.java.getResource("day3.txt").readText()
    val inputs = data.lines()
    val (gamma, epsilon) = findPower(inputs)
    val o2 = getLifeSupportRating(inputs, true)
    val co2 = getLifeSupportRating(inputs, false)
    println("gamma=$gamma")
    println("epsilon=$epsilon")
    println("o2=$o2")
    println("co2=$co2")
    println("power=${gamma * epsilon}")
    println("life=${o2 * co2}")
}