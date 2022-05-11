package wordle

import java.io.FileReader

private const val ANSWER_SOURCE = "src/main/resources/words.txt"

fun main() {
    val answer = Words(FileReader(ANSWER_SOURCE).readLines())
        .createAnswer(STANDARD_DATE)
    printGuide()
    val wordleResult = WordleResult(answer)
    while (!wordleResult.isFinish()) {
        if (wordleResult.checkAnswer(insertAnswer())) {
            printAnswerRound(wordleResult.round)
        }
        printResults(wordleResult.results)
    }
}

