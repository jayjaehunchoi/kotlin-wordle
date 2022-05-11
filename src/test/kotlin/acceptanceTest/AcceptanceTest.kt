package acceptanceTest

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintStream

open class AcceptanceTest {
    private var consoleCaptor: OutputStream = System.out
    private var originOutputStream: PrintStream = System.out

    @BeforeEach
    fun setUp() {
        originOutputStream = System.out
        consoleCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(consoleCaptor))
    }

    protected fun output(): String {
        return consoleCaptor.toString().trim()
    }

    protected fun run(vararg input: String) {
        command(*input)
        runMain()
    }

    protected fun runException(vararg input: String) {
        try {
            run(*input)
        } catch (_: NoSuchElementException) {
        }
    }

    private fun command(vararg input: String) {
        val byte = input.joinToString("\n").toByteArray()
        System.setIn(ByteArrayInputStream(byte))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originOutputStream)
        print(output())
    }

    protected open fun runMain() {}

}
