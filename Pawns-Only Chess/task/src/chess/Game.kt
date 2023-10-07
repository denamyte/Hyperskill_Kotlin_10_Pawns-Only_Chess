package chess

private val askNamesMap = mapOf(
    "First" to ChessColor.White,
    "Second" to ChessColor.Black
)
private val MOVE_REGEX = Regex("([a-h][1-8]){2}")

class Game {
    private lateinit var pMap: Map<ChessColor, Player>
    private val board = Board()

    fun run() {
        pMap = askNamesMap.map { (num, color) ->
            println("$num Player's name:")
            Pair(color, Player(readln(), color))
        }.toMap()
        var color = ChessColor.Black
        var sMove = ""
        main@while (sMove != "exit") {
            println(board)

            color = if (color == ChessColor.Black) ChessColor.White else ChessColor.Black
            val p = pMap[color]!!
            val movePrompt = "${p.name}'s turn:"
            while (true) {
                println(movePrompt)
                sMove = readln()
                if (sMove == "exit") break
                if (!isMoveFormatValid(sMove)) {
                    println("Invalid Input")
                    continue
                }
                val move = Move(sMove)
                if (!board.isPawnOfColorAt(color, move)) {
                    println("No ${color.value} pawn at ${sMove.substring(0, 2)}")
                    continue
                }

                when (board.makeMove(color, move)) {
                    GameState.NORMAL -> break
                    GameState.ERROR -> println("Invalid Input")
                    GameState.STALEMATE -> {
                        println(board)
                        println("Stalemate!")
                        break@main
                    }
                    GameState.WIN -> {
                        println(board)
                        println("${color.value.replaceFirstChar { it.uppercase() }} Wins!")
                        break@main
                    }
                }
            }
        }
        println("Bye!")
    }

    private fun isMoveFormatValid(move: String) = MOVE_REGEX.matches(move)

}