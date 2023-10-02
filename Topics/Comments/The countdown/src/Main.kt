fun main() =
    arrayOf("three", "two", "one", "go")
        .joinToString("\n") { "$it!" }
        .let(::println)