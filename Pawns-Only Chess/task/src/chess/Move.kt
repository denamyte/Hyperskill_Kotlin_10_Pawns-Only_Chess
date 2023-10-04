package chess

private const val LETTERS = "abcdefgh"
class Move(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {

    constructor(rawMove: String) : this(
        LETTERS.indexOf(rawMove[0]),
        rawMove[1].digitToInt() - 1,
        LETTERS.indexOf(rawMove[2]),
        rawMove[3].digitToInt() - 1
    )

    fun flip() = Move(x1, 7 - y1, x2, 7 - y2)
}