package wordle

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import java.io.FileReader
import java.time.LocalDate

class WordsTest {

    @Test
    fun `전체 단어를 가져온다`() {
        val words = createWords()
        words.values shouldHaveSize 2309
    }

    @Test
    fun `오늘의 정답을 결정한다`() {
        val words = createWords()
        val date = LocalDate.of(2019, 6, 20)
        words.createAnswer(date) shouldBe "rebut"
    }

    @Test
    fun `기준일 보다 이전 일자가 들어올 경우 에러가 발생한다`() {
        val words = createWords()
        val date = LocalDate.of(2019, 6, 18)
        shouldThrow<IllegalArgumentException> {
            words.createAnswer(date)
        }.shouldHaveMessage("[ERROR] 기준일 이후의 일자를 입력하세요.")
    }

    private fun createWords(): Words {
        val readLine = FileReader("src/main/resources/words.txt").readLines()
        return Words(readLine)
    }
}
