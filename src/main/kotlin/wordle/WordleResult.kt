package wordle

private const val REQUIRED_SIZE = 6

class WordleResult(
    private val answer: String,
    var results: MutableList<AnswerResults>
) {
    constructor(answer: String) : this(answer, mutableListOf())

    fun checkAnswer(word: String): Boolean {
        require(word.length == REQUIRED_SIZE) { "[ERROR] 정답은 6글자로 입력하세요." }
        val wordSpell = word.toList()
        val answers = mutableListOf<AnswerSymbol>()
        for (idx in wordSpell.indices) {
            answers.add(compareAnswer(wordSpell, idx))
        }
        val answerResults = AnswerResults(answers)
        results.add(answerResults)
        return answerResults.isAnswerCorrect()
    }

    private fun compareAnswer(
        wordSpell: List<Char>,
        idx: Int
    ): AnswerSymbol {
        val answerSpell = answer.toList()
        if (wordSpell[idx] == answerSpell[idx]) {
            return AnswerSymbol.CORRECT
        }
        if (answerSpell.contains(wordSpell[idx])) {
            return AnswerSymbol.DIFFERENT_LOCATION
        }
        return AnswerSymbol.WRONG
    }
}
