fun main() = Array(3) { readln().toInt() }
    .let { it[1] == it.sorted()[1] }
    .let(::println)