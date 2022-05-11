package wordle

class AnswerResults(val symbols: List<AnswerSymbol>) {

    fun isAnswerCorrect(): Boolean {
        val answer = (1..6)
            .map { AnswerSymbol.CORRECT }
        return symbols.zip(answer)
            .all { (symbol, answer) -> symbol == answer }
    }
}
