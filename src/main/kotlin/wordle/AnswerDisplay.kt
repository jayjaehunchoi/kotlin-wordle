package wordle

enum class AnswerDisplay(val display: String) {

    CORRECT("\uD83D\uDFE9"),
    DIFFERENT_LOCATION("\uD83D\uDFE8"),
    WRONG("⬜")
}
