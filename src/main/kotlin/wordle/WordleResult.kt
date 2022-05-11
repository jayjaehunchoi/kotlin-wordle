package wordle

private const val REQUIRED_SIZE = 5

class WordleResult(
    private val answer: String,
    val results: MutableList<AnswerSymbols>
) {

    constructor(answer: String) : this(answer, mutableListOf())

    var round: Int = 0

    fun checkAnswer(word: String): Boolean {
        require(word.length == REQUIRED_SIZE) { "[ERROR] 정답은 5글자로 입력하세요." }
        val wordSpell = word.toList()
        val answerSpell = answer.toList()
        val answers = mutableListOf<AnswerSymbol>()
        for (idx in wordSpell.indices) {
            answers.add(compareAnswer(wordSpell[idx],idx , answerSpell))
        }
        val answerSymbols = AnswerSymbols(answers)
        results.add(answerSymbols)
        round++
        return answerSymbols.isAnswerCorrect()
    }

    private fun compareAnswer(
        wordSpell: Char,
        idx: Int,
        answerSpells: List<Char>
    ): AnswerSymbol {
        if (wordSpell == answerSpells[idx]) {
            return AnswerSymbol.CORRECT
        }
        if (answerSpells.contains(wordSpell)) {
            return AnswerSymbol.DIFFERENT_LOCATION
        }
        return AnswerSymbol.WRONG
    }

    fun isFinish(): Boolean {
        return round == 6
    }
}
