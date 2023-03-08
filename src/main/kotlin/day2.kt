import kotlin.math.abs

class SubmarineLocation {
    var position = 0
    var depth = 0
    var aim = 0

    fun move(cmd: String) {
        val (direction, distanceStr) = cmd.split(" ")
        val distance = distanceStr.toInt()
        when (direction) {
            "forward" -> {
                position += distance
            }
            "up" -> {
                depth += distance
            }
            "down" -> {
                depth -= distance
            }
        }
    }

    fun moveWithAim(cmd: String) {
        val (direction, distanceStr) = cmd.split(" ")
        val distance = distanceStr.toInt()
        when (direction) {
            "forward" -> {
                position += distance
                depth += distance * aim
            }
            "up" -> {
                aim -= distance
            }
            "down" -> {
                aim += distance
            }
        }
    }

    fun getMultipleResult(): Int {
        return position * abs(depth)
    }

    override fun toString(): String {
        return "position=$position, depth=$depth, aim=$aim"
    }
}

fun main() {
    val data = {}::class.java.getResource("day2.txt").readText()
    val location1 = SubmarineLocation()
    val location2 = SubmarineLocation()
    data.lines().forEach {
        location1.move(it)
        location2.moveWithAim(it)
    }

    println(location1.getMultipleResult())
    println(location2.getMultipleResult())
}