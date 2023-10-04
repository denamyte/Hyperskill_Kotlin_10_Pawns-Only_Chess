fun main() =
    MutableList(readln().toInt()) { readln().split(' ').toMutableList() }
        .let { mutableListOf(it.last(), it.first()) }
        .let(::println)