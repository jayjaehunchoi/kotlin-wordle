package wordle

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test

class WordleResultTest {

    @Test
    fun `정답을 확인하고 틀린다`() {
        val result = WordleResult("rebut")
        val isAnswer = result.checkAnswer("elbro")
        isAnswer.shouldBeFalse()
    }

    @Test
    fun `5자를 입력하지 않으면 예외가 발생한다`() {
        val result = WordleResult("rebut")
        shouldThrow<IllegalArgumentException> {
            result.checkAnswer("elbroks")
        }.shouldHaveMessage("[ERROR] 정답은 5글자로 입력하세요.")
    }

    @Test
    fun `정답을 확인하고 정답이다`() {
        val result = WordleResult("rebut")
        val isAnswer = result.checkAnswer("rebut")
        isAnswer.shouldBeTrue()
    }

    @Test
    fun `결과를 확인한다`() {
        val result = WordleResult("rebut")
        result.checkAnswer("elbrk")

        assertSoftly(result.results[0].symbols) {
            it[0] shouldBe AnswerSymbol.DIFFERENT_LOCATION
            it[1] shouldBe AnswerSymbol.WRONG
            it[2] shouldBe AnswerSymbol.CORRECT
            it[3] shouldBe AnswerSymbol.DIFFERENT_LOCATION
            it[4] shouldBe AnswerSymbol.WRONG
        }
    }

    @Test
    fun `6회 실행할 경우 종료되었는지 확인한다`() {
        val result = WordleResult("rebut")
        for (idx in 0..5) {
            result.checkAnswer("elbro")
        }
        result.isFinish().shouldBeTrue()
    }

    @Test
    fun `정답을 맞힐 경우 종료되었는지 확인한다`() {
        val result = WordleResult("rebut")
        result.checkAnswer("rebut")

        result.isFinish().shouldBeTrue()
    }
}
