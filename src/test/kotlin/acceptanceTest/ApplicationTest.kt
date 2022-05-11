package acceptanceTest

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.Test
import wordle.ANSWER_SOURCE
import wordle.TODAY
import wordle.Words
import wordle.main
import java.io.FileReader

class ApplicationTest : AcceptanceTest() {

    @Test
    fun `5자를 입력하지 않으면 실패한다`() {
        shouldThrow<IllegalArgumentException> {
            runException("acxd")
        }.shouldHaveMessage("[ERROR] 정답은 5글자로 입력하세요.")
    }

    @Test
    fun `6번의 기회를 끝내면 게임을 종료한다`() {
        run("aaaaa", "aaaaa", "aaaaa", "aaaaa", "aaaaa", "aaaaa")
        output().shouldNotContain("/6")
    }

    @Test
    fun `정답을 맞히면 라운드를 출력한다`() {
        val answer = Words(FileReader(ANSWER_SOURCE).readLines()).createAnswer(TODAY)
        run("aaaaa", answer)
        output().shouldContain("2/6")
    }

    override fun runMain() {
        main()
    }
}
