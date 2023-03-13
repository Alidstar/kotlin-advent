fun decodeSevenSegment(example: List<String>, toDecode: List<String>): Int {
    val sortedExample = example.map { it.toCharArray().sorted().joinToString("") }.sortedBy { it.length }
    val sortedToDecode = toDecode.map { it.toCharArray().sorted().joinToString("") }

    // find 1, 4, 7, 8 (index 0, 1, 2, 9
    val decodeResult = Array<String?>(10) { null }
    decodeResult[1] = sortedExample[0]
    decodeResult[7] = sortedExample[1]
    decodeResult[4] = sortedExample[2]
    decodeResult[8] = sortedExample[9]

    // find 0, 6, 9 (index 6, 7, 8)
    // 6 not contain all 1
    // 9 contain all 4
    // 0 else
    val decodeCharArray1 = decodeResult[1]!!.toCharArray()
    val decodeCharArray4 = decodeResult[4]!!.toCharArray()
    for (i in 6..8) {
        val thisCharArray = sortedExample[i].toCharArray()
        if ((decodeResult[6] == null) and !decodeCharArray1.all { thisCharArray.contains(it) }) { // is 6
            decodeResult[6] = sortedExample[i]
        } else if ((decodeResult[9] == null) and decodeCharArray4.all { thisCharArray.contains(it) }) { // is 9
            decodeResult[9] = sortedExample[i]
        } else {
            decodeResult[0] = sortedExample[i]
        }
    }

    // find 2, 3, 5 (index 3, 4, 5)
    // 3 contain all 1
    // 5 9 contain all
    // 2 else
    val decodeCharArray9 = decodeResult[9]!!.toCharArray()
    for (i in 3..5) {
        val thisCharArray = sortedExample[i].toCharArray()
        if ((decodeResult[3] == null) and decodeCharArray1.all { thisCharArray.contains(it) }) { // is 3
            decodeResult[3] = sortedExample[i]
        } else if ((decodeResult[5] == null) and thisCharArray.all { decodeCharArray9.contains(it) }) { // is 5
            decodeResult[5] = sortedExample[i]
        } else {
            decodeResult[2] = sortedExample[i]
        }
    }

    var decoded = ""
    for (text in sortedToDecode) {
        val decodedNumber = decodeResult.indexOf(text)
        decoded += decodedNumber
    }

    return decoded.toInt()
}

fun main() {
    val data = {}::class.java.getResource("day8.txt")!!.readText()
    val pairs = ArrayList<Pair<List<String>, List<String>>>()
    data.lines().forEach {
        val (a, b) = it.split(" | ")
        val pair = Pair(a.split(" "), b.split(" "))
        pairs.add(pair)
    }

    val backData = pairs.flatMap { it.second }
    println("count 1,4,7,8 = ${backData.count { (it.length == 2) or (it.length == 3) or (it.length == 4) or (it.length == 7) }}")

    var sum = 0
    for (pair in pairs) {
        val value = decodeSevenSegment(pair.first, pair.second)
        println("${pair.second} => $value")
        sum += value
    }
    println("decoded sum = $sum")
}