package chess

enum class ChessColor(val sym: Char, val oppSym: Char, val value: String) {
    Black('B', 'W', "black"),
    White('W', 'B', "white");

    fun oppColor() = if (this == White) Black else White
}