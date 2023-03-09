import kotlin.math.abs

class CloudMap {
    private val cloudDensity = mutableMapOf<String, Int>()

    private fun addPoint(x: Int, y: Int) {
        val key = "$x,$y"
        cloudDensity[key] = cloudDensity[key]?.plus(1) ?: 1
    }

    fun plotHorizontalAndVertical(x1: Int, y1: Int, x2: Int, y2: Int) {
        if ((x1 == x2) and (y1 == y2)) {
            addPoint(x1, y1)
        } else if (y1 == y2) {
            if (x1 > x2) for (x in x2..x1) addPoint(x, y1)
            else for (x in x1..x2) addPoint(x, y1)
        } else if (x1 == x2) {
            if (y1 > y2) for (y in y2..y1) addPoint(x1, y)
            else for (y in y1..y2) addPoint(x1, y)
        }
    }

    fun plotDiagonal(x1: Int, y1: Int, x2: Int, y2: Int) {
        val vx = x2 - x1
        val vy = y2 - y1
        if (abs(vx) == abs(vy)) {
            if ((vx < 0) and (vy < 0)) for (i in 0..abs(vx)) addPoint(x1 - i, y1 - i)
            else if ((vx > 0) and (vy < 0)) for (i in 0..abs(vx)) addPoint(x1 + i, y1 - i)
            else if ((vx < 0) and (vy > 0)) for (i in 0..abs(vx)) addPoint(x1 - i, y1 + i)
            else if ((vx > 0) and (vy > 0)) for (i in 0..abs(vx)) addPoint(x1 + i, y1 + i)
        }
    }

    fun countDensityMoreThanOrEqual(number: Int): Int {
        return cloudDensity.count { it.value >= number }
    }
}

fun main() {
    val data = {}::class.java.getResource("day5.txt").readText()
    val cloudMap = CloudMap()
    data.lines().map {
        val (from, to) = it.split(" -> ")
        val (fromX, fromY) = from.split(",")
        val (toX, toY) = to.split(",")
        cloudMap.plotHorizontalAndVertical(fromX.toInt(), fromY.toInt(), toX.toInt(), toY.toInt())
    }
    println("count VH >= 2 ${cloudMap.countDensityMoreThanOrEqual(2)}")
    data.lines().map {
        val (from, to) = it.split(" -> ")
        val (fromX, fromY) = from.split(",")
        val (toX, toY) = to.split(",")
        cloudMap.plotDiagonal(fromX.toInt(), fromY.toInt(), toX.toInt(), toY.toInt())
    }
    println("count VHD >= 2 ${cloudMap.countDensityMoreThanOrEqual(2)}")
}