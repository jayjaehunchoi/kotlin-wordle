package wordle

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WordleResultTest {

    @DisplayName("정답을 확인하고 틀린다.")
    @Test
    fun checkAnswerWrong() {
        val result = WordleResult("reboot")
        val isAnswer = result.checkAnswer("elbrok")
        isAnswer.shouldBeFalse()
    }

    @DisplayName("5자를 입력하지 않으면 예외가 발생한다.")
    @Test
    fun checkAnswerNotValidInput() {
        val result = WordleResult("reboot")
        shouldThrow<IllegalArgumentException> {
            result.checkAnswer("elbrokss")
        }.shouldHaveMessage("[ERROR] 정답은 5글자로 입력하세요.")
    }

    @DisplayName("정답을 확인하고 정답이다.")
    @Test
    fun checkAnswerCorrect() {
        val result = WordleResult("reboot")
        val isAnswer = result.checkAnswer("reboot")
        isAnswer.shouldBeTrue()
    }

    @DisplayName("결과를 확인한다.")
    @Test
    fun checkResult() {
        val result = WordleResult("reboot")
        result.checkAnswer("elbrok")

        assertSoftly(result.results[0].symbols) {
            it[0] shouldBe AnswerSymbol.DIFFERENT_LOCATION
            it[1] shouldBe AnswerSymbol.WRONG
            it[2] shouldBe AnswerSymbol.CORRECT
            it[3] shouldBe AnswerSymbol.DIFFERENT_LOCATION
            it[4] shouldBe AnswerSymbol.CORRECT
            it[5] shouldBe AnswerSymbol.WRONG
        }
    }
}
