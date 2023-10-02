fun main() {
    val totalSeconds = System.currentTimeMillis() / 1000 // do not change this line
    val h = totalSeconds / 60 / 60 % 24
    val m = totalSeconds / 60 % 60
    val s = totalSeconds % 60
    println("$h:$m:$s")
}