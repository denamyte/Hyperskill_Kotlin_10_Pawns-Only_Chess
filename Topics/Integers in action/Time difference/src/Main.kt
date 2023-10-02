val secs = listOf(60 * 60, 60, 1)

fun moment() = secs.sumOf { it * readln().toInt() }

fun main() = println(-moment() + moment())