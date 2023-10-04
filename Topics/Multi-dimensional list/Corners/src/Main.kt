fun main() =
    List(readln().toInt()) { readln().split(' ') }
        .let { listOf(it.first(), it.last()) }
        .forEach { println("${it.first()} ${it.last()}") }