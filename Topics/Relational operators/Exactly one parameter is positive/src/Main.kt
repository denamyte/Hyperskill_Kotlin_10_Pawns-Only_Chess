const val N = 3

fun main() = println(
    Array(N) { readln().toInt() }
        .count { it > 0 } == 1
)