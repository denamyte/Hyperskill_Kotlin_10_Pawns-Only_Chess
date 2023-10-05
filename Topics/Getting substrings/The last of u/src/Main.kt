fun main() =
    readln().run {
        indexOfLast { it == 'u' }.let {
            println("${substring(0, it + 1)}${substring(it + 1).uppercase()}")
        }
    }