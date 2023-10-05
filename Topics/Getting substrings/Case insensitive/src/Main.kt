fun main() = println(MutableList(2) { readln().lowercase() }.toSet().size == 1)
