class BingoBoard(boardValues: List<List<Int>>) {
    companion object {
        private const val size = 5
    }

    private var markCounts = IntArray(10) { 0 } // 0-4 horizontal, 5-9 vertical
    private val numberMap = mutableMapOf<Int, Pair<Int, Int>>()
    private val allNumbers = ArrayList<Int>(size * size)
    private val markedNumbers = ArrayList<Int>()
    var win = false

    init {
        for (i in 0 until size) {
            for (j in 0 until size) {
                numberMap[boardValues[i][j]] = Pair(j, i + 5)
                allNumbers.add(boardValues[i][j])
            }
        }
    }

    fun mark(number: Int): Boolean {
        win = numberMap[number]?.let {
            markedNumbers.add(number)
            val (row, col) = it
            (++markCounts[row] == size) or (++markCounts[col] == size)
        } == true
        return win
    }

    fun getUnmarkedSum(): Int {
        val unmarkedNumber = allNumbers - markedNumbers
        return unmarkedNumber.reduce { acc, value -> acc + value }
    }
}

fun main() {
    val data = {}::class.java.getResource("day4.txt").readText()
    val inputs = data.lines()
    val numbers = inputs[0].split(",").map { it.toInt() }
    val boards = ArrayList<BingoBoard>()
    for (i in 2 until inputs.size step 6) {
        val boardValues = listOf(
            inputs[i].split(" ").filter { it.isNotEmpty() }.map { it.toInt() },
            inputs[i + 1].split(" ").filter { it.isNotEmpty() }.map { it.toInt() },
            inputs[i + 2].split(" ").filter { it.isNotEmpty() }.map { it.toInt() },
            inputs[i + 3].split(" ").filter { it.isNotEmpty() }.map { it.toInt() },
            inputs[i + 4].split(" ").filter { it.isNotEmpty() }.map { it.toInt() },
        )
        boards.add(BingoBoard(boardValues))
    }

    var winCount = 0
    numbers.forEach { number ->
        boards.forEachIndexed { i, board ->
            if (!board.win) {
                val win = board.mark(number)
                if (win) {
                    winCount++
                    if (winCount == 1) {
                        println("first win board #$i")
                        println("score = ${board.getUnmarkedSum() * number}")
                    } else if (winCount == boards.size) {
                        println("last win board #$i")
                        println("score = ${board.getUnmarkedSum() * number}")
                    }
                }
            }
        }
    }
}