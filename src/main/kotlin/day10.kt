import java.util.*

fun main() {
    val data = {}::class.java.getResource("day10.txt")!!.readText()
    val lines = data.lines().map { it.chunked(1) }

    val symbolPair = mapOf(
        ")" to "(",
        "]" to "[",
        "}" to "{",
        ">" to "<",
    )

    val symbolPoint1 = mapOf(
        ")" to 3,
        "]" to 57,
        "}" to 1197,
        ">" to 25137,
    )
    val symbolPoint2 = mapOf(
        "(" to 1,
        "[" to 2,
        "{" to 3,
        "<" to 4,
    )

    var mistakePoint1 = 0
    val mistakePoint2 = ArrayList<Long>()
    for (line in lines) {
        var corrupted = false
        val stack = Stack<String>()
        for (symbol in line) {
            when {
                symbolPair[symbol] == null -> stack.push(symbol)
                else -> {
                    if (stack.pop() != symbolPair[symbol]) {
                        mistakePoint1 += symbolPoint1[symbol]!!
                        corrupted = true
                        break
                    }
                }
            }
        }
        if (!corrupted) {
            var point: Long = 0
            while (!stack.empty()) {
                val symbol = stack.pop()
                point = (point * 5) + symbolPoint2[symbol]!!
            }
            mistakePoint2.add(point)
        }
    }

    println(mistakePoint1)
    println(mistakePoint2.sorted()[mistakePoint2.size / 2])
}
