package wordle

import java.io.FileReader
import java.time.LocalDate

private const val ANSWER_SOURCE = "src/main/resources/words.txt"
private val TODAY = LocalDate.now()

fun main() {
    val answer = Words(FileReader(ANSWER_SOURCE).readLines())
        .createAnswer(TODAY)
    printGuide()
    val wordleResult = WordleResult(answer)
    while (!wordleResult.isFinish()) {
        if (wordleResult.checkAnswer(insertAnswer())) {
            printAnswerRound(wordleResult.round)
        }
        printResults(wordleResult.results)
    }
}

