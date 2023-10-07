val countries = mapOf(
    "Germany" to "Euro",
    "Mali" to "CFA franc",
    "Dominica" to "Eastern Caribbean dollar",
    "Canada" to "Canadian dollar",
    "Spain" to "Euro",
    "Australia" to "Australian dollar",
    "Brazil" to "Brazilian real",
    "Senegal" to "CFA franc",
    "France" to "Euro",
    "Grenada" to "Eastern Caribbean dollar",
    "Kiribati" to "Australian dollar",
)

fun main() = println(
    readln().split(" ")
        .map { countries[it] }
        .toSet().size == 1
)
