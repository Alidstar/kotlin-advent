fun scanDeeper(inputs: List<Int>): Int {
    return inputs.windowed(2).count { (a, b) -> a < b }
}

fun sumThree(inputs: List<Int>): List<Int> {
    return inputs.windowed(3).map { (a, b, c) -> a + b + c }
}

fun main() {
    val data = {}::class.java.getResource("day1.txt").readText()
    val intArr = data.lines().map { it.toInt() }
    val countDeeper = scanDeeper(intArr)
    val countSumThreeDeeper = scanDeeper(sumThree(intArr))

    println(countDeeper)
    println(countSumThreeDeeper)
}