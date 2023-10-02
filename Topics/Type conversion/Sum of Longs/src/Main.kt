fun main() =
    Array(2) { readln().toLong() }
        .sum()
        .let(::println)