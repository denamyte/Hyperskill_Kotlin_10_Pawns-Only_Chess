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

    fun makeMove(color: ChessColor, move: Move): GameState {
        val m = if (color == ChessColor.White) move else move.flip()

        // Check if this move is taking an opposite pawn
        if (m.x1 != m.x2) {
            if (abs(m.x2 - m.x1) != 1 || m.y2 - m.y1 != 1) return GameState.ERROR
            if (field[move.y2][move.x2] == color.opp.sym) {
                // todo: count taking pawns...

                return finishMove(color, move)
            }
            if (!lastMove.isDouble) return GameState.ERROR
            if ((lastMove.y1 + lastMove.y2) / 2 != move.y2 ||
                lastMove.x2 != move.x2) return GameState.ERROR

            // It's an "en passant" taking !!
            field[lastMove.y2][lastMove.x2] = ' '  // Erasing the opposite pawn
            // todo: count taking pawns...

            return finishMove(color, move)
        }

        // Check if the coordinates difference is valid
        if (m.y1 == 1) {
            if (m.y2 - m.y1 !in 1..2) return GameState.ERROR
        } else {
            if (m.y2 - m.y1 != 1) return GameState.ERROR
        }

        // Check if the coordinates are empty on the pawn's way
        val f = fieldByColor[color]!!
        for (y in m.y1 + 1..m.y2) {
            if (f[y][m.x1] != ' ') return GameState.ERROR
        }

        return finishMove(color, move)
    }

    private fun finishMove(color: ChessColor, move: Move): GameState {
        field[move.y1][move.x1] = ' '
        field[move.y2][move.x2] = color.sym
        lastMove = move
        return checkGameState(color)
    }

    /**
     * Check if the game is over:
     * 1) One of the player reaches the final line for its color.
     * 2) One of the colors is eliminated.
     * 3) One of the color cannot move.
     */
    private fun checkGameState(moveColor: ChessColor): GameState {
        // 1)
        if (lastMove.y2 == 0 || lastMove.y2 == 7) return GameState.WIN

        // 2
        val oppCount = field.flatten().count { it == moveColor.opp.sym }
        if (oppCount == 0) return GameState.WIN

        // 3
        run {
            val c = moveColor.opp
            val f = fieldByColor[c]!!
            var staleCount = 0
            for (rankIndex in 1 until 6) { // the first line and the last two ones do not matter
                val rankLine = f[rankIndex]
                for (fileIndex in 0..7) {
                    val sym = rankLine[fileIndex]
                    val nextRank = f[rankIndex + 1]
                    if (sym == c.sym && nextRank[fileIndex] != ' ') {
                        // check the coordinates on the nextRank to the left and to the right of fileIndex
                        val leftBusy = fileIndex == 0 || nextRank[fileIndex - 1] != moveColor.sym
                        val rightBusy = fileIndex == 7 || nextRank[fileIndex + 1] != moveColor.sym
                        if (leftBusy && rightBusy) 
                            staleCount++
                    }
                }
            }
            if (staleCount == oppCount)
                return GameState.STALEMATE
        }

        return GameState.NORMAL
    }
}