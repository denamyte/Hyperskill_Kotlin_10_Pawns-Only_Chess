fun main() {    
    val (a, b, h) = Array(3) { readln().toInt() }
    println(
        if (h < a) "Deficiency" else if (h > b) "Excess" else "Normal"
    )
}