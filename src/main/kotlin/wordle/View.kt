package wordle

fun printGuide() {
    println("WORDLE을 6번 만에 맞춰 보세요.\n" +
            "시도의 결과는 타일의 색 변화로 나타납니다.")
}

fun insertAnswer(): String {
    println("정답을 입력해 주세요.")
    return readln()
}
