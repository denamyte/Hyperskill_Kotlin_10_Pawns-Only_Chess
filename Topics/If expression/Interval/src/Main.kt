val intervals = listOf(-14..12, 15..16, 19..Int.MAX_VALUE)
val result = mapOf(true to "True", false to "False")

fun main() = println(
    readln().toInt().run { result[intervals.any { this in it }] }
)
