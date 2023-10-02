package chess

private const val SIZE = 8
private const val SEP_LINE = "  +---+---+---+---+---+---+---+---+\n"
private const val LETTERS = "abcdefgh"
private const val LETTER_LINE = "    a   b   c   d   e   f   g   h"
private val DATA_LINE = "%s | ".repeat(9).trim() + "\n"
private val rankRange = 8 downTo 1

private fun getRank(c: Char) = MutableList(SIZE) { c }

class Board {
    private val data = MutableList(SIZE) { getRank(' ') }

    init {
        data[1] = getRank('B')
        data[6] = getRank('W')
    }

    override fun toString() = buildString {
        append(SEP_LINE)
        append(
            rankRange.joinToString(SEP_LINE) {
                DATA_LINE.format(it, *data[convertRankIndex(it)].toTypedArray())
            }
        )
        append(SEP_LINE)
        append(LETTER_LINE)
    }

    private fun convertRankIndex(index: Int) = SIZE - index
    private fun convertFilesIndex(letter: Char) = LETTERS.indexOf(letter)
}