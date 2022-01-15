package tictactoe

fun main() {
    menu()
}
fun menu () {
    val inputList = MutableList(9) { ' ' }
    val dependCoordinates = listOf("1 1", "1 2", "1 3", "2 1","2 2", "2 3", "3 1", "3 2", "3 3")
    var usersTurn : String

    printBoard(inputList)

    for (i in 1..9) {

        println("Enter the coordinates:")

        while (true) {
            usersTurn = readLine()!!

            when {
                usersTurn.contains(Regex("""[^0123456789 ]""")) || (usersTurn.length > 3) -> {
                    println("You should enter numbers! if the user enters non-numeric symbols in the coordinates input.")
                }
                usersTurn.contains(Regex("""[^123 ]""")) -> {
                    println("Coordinates should be from 1 to 3!")
                }
                inputList[dependCoordinates.indexOf(usersTurn)] != ' ' -> {
                    println("This cell is occupied! Choose another one! if the cell is not empty.")
                }
                i % 2 == 1 -> {
                    inputList[dependCoordinates.indexOf(usersTurn)] = 'X'
                    break
                }
                else -> {
                    inputList[dependCoordinates.indexOf(usersTurn)] = 'O'
                    break
                }
            }
        }

        printBoard(inputList)

        when {
            i > 4 && whoWinCheck(inputList).isLetter() -> {
                println("${whoWinCheck(inputList)} wins")
                return
            }
            i == 9 -> {
                println("Draw")
                return
            }
            else -> {
            }
        }
    }
}

fun printBoard (inputList: MutableList<Char>) {
    val upDownLines = MutableList(9) { '-' }
    println(upDownLines.joinToString(""))
    println("| ${inputList.subList(0, 3).joinToString(" ")} |")
    println("| ${inputList.subList(3, 6).joinToString(" ")} |")
    println("| ${inputList.subList(6, 9).joinToString(" ")} |")
    println(upDownLines.joinToString(""))
}

fun whoWinCheck (inputList: MutableList<Char>) : Char {
    var winCount = ' '
    val winCombination = listOf(
        listOf(0, 4, 8),
        listOf(6, 4, 2),
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8)
    )

    for (i in winCombination.indices) {
        if (
            inputList[winCombination[i][0]] == inputList[winCombination[i][1]]
            && inputList[winCombination[i][1]] == inputList[winCombination[i][2]]
            && inputList[winCombination[i][0]].isLetter()
        ) {
            winCount = inputList[winCombination[i][0]]
        }
    }

    return winCount
}