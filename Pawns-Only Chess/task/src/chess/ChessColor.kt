package chess

enum class ChessColor(val sym: Char, val value: String) {
    Black('B', "black"),
    White('W', "white");

    /** The opposite color */
    val opp get() = if (this == White) Black else White
}