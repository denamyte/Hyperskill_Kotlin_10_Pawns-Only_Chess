fun main() = List(3) { readln().toInt() }
    .let { it[0] == it.sorted()[1] }
    .let(::println)
