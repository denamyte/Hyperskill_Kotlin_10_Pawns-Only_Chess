fun main() {
    val cups = readln().toInt()
    val weekend = readln().toBoolean()
    println(weekend && 15 <= cups && cups <= 25 ||
            ! weekend && 10 <= cups && cups <= 20
    )
}