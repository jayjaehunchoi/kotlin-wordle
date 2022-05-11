package wordle

import java.time.LocalDate
import java.time.Period

val STANDARD_DATE: LocalDate = LocalDate.of(2019, 6, 19)

class Words(val values: List<String>) {
    fun createAnswer(date: LocalDate): String {
        require(date.isAfter(STANDARD_DATE)) {"[ERROR] 기준일 이후의 일자를 입력하세요."}
        val period = Period.between(STANDARD_DATE, date)
        val periodDays = (period.years * 365) + (period.months * 12) + period.days
        return values[periodDays % values.size]
    }
}
