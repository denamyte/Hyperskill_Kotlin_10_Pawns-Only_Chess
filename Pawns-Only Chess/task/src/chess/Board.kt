package chess

private const val SIZE = 8
private const val SEP_LINE = "  +---+---+---+---+---+---+---+---+\n"
private const val LETTER_LINE = "    a   b   c   d   e   f   g   h\n"
private val DATA_LINE = "%s | ".repeat(9).trim() + "\n"
private val rankRange = 8 downTo 1

private fun createRank(c: Char) = MutableList(SIZE) { c }

class Board {
    private val field: MutableList<MutableList<Char>> = MutableList(SIZE) { createRank(' ') }
    private val fieldByColor = mapOf(
        ChessColor.White to field,
        ChessColor.Black to field.reversed()
    )

    init {
        field[1] = createRank('W')
        field[6] = createRank('B')
    }

    override fun toString() = buildString {
        append(SEP_LINE)
        append(
            rankRange.joinToString(SEP_LINE) {
                DATA_LINE.format(it, *field[it - 1].toTypedArray())
            }
        )
        append(SEP_LINE)
        append(LETTER_LINE)
    }

    fun isMoveValid(color: ChessColor, move: Move): Boolean {
        val m = if (color == ChessColor.White) move else move.flip()

        // Check if both coordinates are on the same vertical line
        if (m.x1 != m.x2) return false

        // Check if the coordinates difference is valid
        if (m.y1 == 1) {
            if (m.y2 - m.y1 !in 1..2) return false
        } else {
            if (m.y2 - m.y1 != 1) return false
        }

        // Check if the coordinates are empty on the pawn's way
        val f = fieldByColor[color]!!
        for (y in m.y1 + 1..m.y2) {
            if (f[y][m.x1] != ' ') return false
        }

        return true
    }

    fun isPawnOfColorAt(color: ChessColor, move: Move) = field[move.y1][move.x1] == color.sym

    fun makeMove(color: ChessColor, move: Move) {
        field[move.y1][move.x1] = ' '
        field[move.y2][move.x2] = color.sym
    }
}