package wordle

import java.io.FileReader
import java.time.LocalDate

fun main() {
    val answer = Words(FileReader("src/main/resources/words.txt").readLines())
        .createAnswer(LocalDate.now())
    printGuide()
    val wordleResult = WordleResult(answer)
    while (!wordleResult.checkAnswer(insertAnswer()) && !wordleResult.isFinish()) {
        printResults(wordleResult.results)
    }
    val results = wordleResult.results
    if (AnswerSymbols(results[results.lastIndex].symbols).isAnswerCorrect()) {
        printAnswerRound(wordleResult.round)
    }
    printResults(results)

}

