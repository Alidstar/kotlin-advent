fun main() {
    val data = {}::class.java.getResource("day6.txt")!!.readText()
    val lanternFish = data.split(",").map { it.toInt() }

    val lifeCount = mutableMapOf<Int, Long>()
    for (i in 0..8) lifeCount[i] = 0
    for (life in lanternFish) lifeCount[life] = lifeCount[life]!! + 1

//    println("day 0 $lifeCount ${lifeCount.values.reduce { acc, i -> acc + i }}")

    val day = 256
    for (d in 1..day) {
        lifeCount[8] = lifeCount[0]!!.also {
            for (i in 0..7) lifeCount[i] = lifeCount[i + 1]!!
            lifeCount[6] = lifeCount[6]!! + it
        }
//        println("day $d $lifeCount ${lifeCount.values.reduce { acc, i -> acc + i }}")
        println("day $d ${lifeCount.values.reduce { acc, i -> acc + i }}")
    }
}