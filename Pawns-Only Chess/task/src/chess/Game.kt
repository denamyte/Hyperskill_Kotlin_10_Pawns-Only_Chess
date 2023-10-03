package chess

private val askNamesMap = mapOf(
    "First" to ChessColor.White,
    "Second" to ChessColor.Black
)
private val MOVE_REGEX = Regex("([a-h][1-8]){2}")

class Game {
    private val players: List<Player>
    private val board = Board()

    init {
        players = getPlayers()
    }

    fun run() {
        println(board)
        println()
        var pIndex = -1
        var move = ""
        while (move != "exit") {
            pIndex = (pIndex + 1) % players.size
            val p = players[pIndex]
            val movePrompt = "${p.name}'s turn:"
            while (true) {
                println(movePrompt)
                move = readln()
                if (move == "exit" || isMoveValid(move)) break
                println("Invalid Input")
            }
        }
        println("Bye!")
    }

    private fun getPlayers(): List<Player> {
        return askNamesMap.map { (num, color) ->
            println("$num Player's name:")
            Player(readln(), color)
        }
    }

    private fun isMoveValid(move: String) = MOVE_REGEX.matches(move)
}