package wordle

fun printGuide() {
    println("WORDLE을 6번 만에 맞춰 보세요.")
    println("시도의 결과는 타일의 색 변화로 나타납니다.")
}

fun insertAnswer(): String {
    println("정답을 입력해 주세요.")
    return readln()
}

fun printResults(results: List<AnswerSymbols>) {
    val result = results.joinToString("\n") {
        it.symbols
            .joinToString("") { symbol -> symbol.display }
    }
    println(result)
}

fun printAnswerRound(round: Int) {
    println("$round/6")
}
