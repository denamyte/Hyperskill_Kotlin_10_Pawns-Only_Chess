import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    Array(4) { scanner.nextInt() }.forEach(::println)
}