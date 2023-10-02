// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    val d0 = 1.0
    val f0 = 1f
    val l0 = 30L

    val d: Double = 1.0 % 10f         // Line 1
    val f: Float = d0.toLong() + 1.0  // Line 2
    val l: Long = f0.toInt() + 4      // Line 3
    val n: Int = l0.toByte() + 2      // Line 4
}
