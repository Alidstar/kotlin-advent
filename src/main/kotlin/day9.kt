fun findLowestPoints(dataArray: List<List<Int>>): List<Int> {
    val dataChecked = Array(dataArray.size) { Array(dataArray[0].size) { false } }
    val lowestPoints = mutableListOf<Int>()
    for (i in dataArray.indices) {
        for (j in dataArray[0].indices) {
            if (dataChecked[i][j]) continue
            val thisValue = dataArray[i][j]

            // check with up
            try {
                if (thisValue < dataArray[i - 1][j]) dataChecked[i - 1][j] = true
                else continue
            } catch (_: IndexOutOfBoundsException) {
            }

            // check with down
            try {
                if (thisValue < dataArray[i + 1][j]) dataChecked[i + 1][j] = true
                else continue
            } catch (_: IndexOutOfBoundsException) {
            }

            // check with left
            try {
                if (thisValue < dataArray[i][j - 1]) dataChecked[i][j - 1] = true
                else continue
            } catch (_: IndexOutOfBoundsException) {
            }

            // check with right
            try {
                if (thisValue < dataArray[i][j + 1]) dataChecked[i][j + 1] = true
                else continue
            } catch (_: IndexOutOfBoundsException) {
            }

            lowestPoints.add(thisValue)
        }
    }
    return lowestPoints
}

fun findBasinSize(dataArray: List<List<Int>>, dataChecked: Array<Array<Boolean>>, i: Int, j: Int): Int {
    try {
        if (dataChecked[i][j]) return 0
        dataChecked[i][j] = true
        if (dataArray[i][j] == 9) return 0

        val up = findBasinSize(dataArray, dataChecked, i - 1, j)
        val down = findBasinSize(dataArray, dataChecked, i + 1, j)
        val left = findBasinSize(dataArray, dataChecked, i, j - 1)
        val right = findBasinSize(dataArray, dataChecked, i, j + 1)

        return 1 + up + down + left + right
    } catch (e: IndexOutOfBoundsException) {
        return 0
    }
}

fun findBasinSizes(dataArray: List<List<Int>>): List<Int> {
    val dataChecked = Array(dataArray.size) { Array(dataArray[0].size) { false } }
    val basinSizes = mutableListOf<Int>()

    for (i in dataArray.indices) {
        for (j in dataArray[0].indices) {
            if (dataChecked[i][j]) continue
            val size = findBasinSize(dataArray, dataChecked, i, j)
            if (size > 0) basinSizes.add(size)
        }
    }
    return basinSizes
}

fun main() {
    val data = {}::class.java.getResource("day9.txt")!!.readText()
    val dataArray = data.lines().map { it.chunked(1).map { c -> c.toInt() } }

    println(findLowestPoints(dataArray).fold(0) { acc, value -> acc + value + 1 })

    val basinSizes = findBasinSizes(dataArray).sorted()
    val max3basins = basinSizes.takeLast(3)
    println(max3basins.fold(1) { acc, value -> acc * value })
}