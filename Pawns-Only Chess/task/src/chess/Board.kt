package chess

import kotlin.math.abs

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
    private var lastMove: Move = Move(0, 0, 0, 0)

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

    fun isPawnOfColorAt(color: ChessColor, move: Move) = field[move.y1][move.x1] == color.sym

    fun makeMove(color: ChessColor, move: Move): Boolean {
        val m = if (color == ChessColor.White) move else move.flip()

        // Check if this move is taking an opposite pawn
        if (m.x1 != m.x2) {
            if (abs(m.x2 - m.x1) != 1 || m.y2 - m.y1 != 1) return false
            if (field[move.y2][move.x2] == color.oppColor().sym) {
                // todo: count taking pawns...

                return finishMove(color, move)
            }
            if (!lastMove.isDouble) return false
            if ((lastMove.y1 + lastMove.y2) / 2 != move.y2 ||
                lastMove.x2 != move.x2) return false

            // It's an "en passant" taking !!
            field[lastMove.y2][lastMove.x2] = ' '  // Erasing the opposite pawn
            // todo: count taking pawns...

            return finishMove(color, move)
        }

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

        return finishMove(color, move)
    }

    private fun finishMove(color: ChessColor, move: Move): Boolean {
        field[move.y1][move.x1] = ' '
        field[move.y2][move.x2] = color.sym
        lastMove = move
        return true
    }
}