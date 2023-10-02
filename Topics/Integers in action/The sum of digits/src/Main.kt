fun main() {
    var number = readln().toInt()
    var sum = 0
    while (number > 0) {
        sum += number % 10
        number /= 10
    }
    println(sum)
}