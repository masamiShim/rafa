package com.freitech.roger.api.rafa.scrapings.courts.asaka.pages

import com.freitech.roger.api.rafa.scrapings.ChromeDriver
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.courtList.AsakaCourtListHeader
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.courtList.AvailableDateModel
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList.AsakaCourtListRowModel
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

/**
 * 選択したコートと選択した期間が表示されるページ。
 * 照会したい個所を複数選択し、次へボタンですすむ
 * 【実装方針】
 * コートの名称表示されている箇所でコート判断。
 * ctl00 ~ ctl04の範囲内で表示されている。
 * 文字(〇, △)を含んでいる項目を選択し、そこら辺をふんちゃかする。
 * が、選択した次のページがつらいので選択した分だけスレッド構えてどうにかするか....
 */
class CourtListViewPage(val driver: ChromeDriver, val startDate: LocalDate) {
    // 1コートだけでやろう。そうしよう
    private val asakaCourtListHeader: AsakaCourtListHeader = AsakaCourtListHeader(1)
    private val asakaCourtListRow: AsakaCourtListRowModel = AsakaCourtListRowModel()

    private val nextButton = By.xpath("//*[@id=\"ucPCFooter_btnForward\"]")
    private val DATE_COL_RANGE: IntRange = (0..31)

    private var selectedCount: Int = 0

    fun getYearMonth(): YearMonth {
        val yearMonthElem = driver.getSelf().findElement(asakaCourtListHeader.getYearMonthPath())
        return convertYearMonth(yearMonthElem.text)
    }

    fun getAvailableMap(): MutableMap<LocalDate, AvailableDateModel> {
        val dateList: MutableMap<LocalDate, AvailableDateModel> = LinkedHashMap()

        for (col in DATE_COL_RANGE) {
            try {
                val targetDate = startDate.plusDays(col.toLong())
                // FIXME:祝日取得できるようにして祝日もとれるようにしたい
                // 10件まで選択なので、土日のみ取るようにする。
                if (isNotHoliday(targetDate.dayOfWeek)) {
                    // 一旦はweekdayは無視
                    continue
                }

                val availableCourtLinkPath: By = asakaCourtListRow.getAvailableSymbolPath(targetDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                val text = driver.getSelf().findElement(availableCourtLinkPath).text

                if (text.contains("△") || text.contains("〇")) {
                    dateList.put(targetDate, AvailableDateModel(targetDate, text, availableCourtLinkPath))
                    System.out.println("dataIn: $targetDate -> $text")
                    selectedCount += 1
                }
            } catch (nse: NoSuchElementException) {
                // 曜日が抜けてたりで取れない場合もあるので飛ばす
                System.out.println("skip")
                continue
            }
        }
        return dateList
    }

    fun clickAll(availableLinkSet: Set<By>) {
        availableLinkSet.forEach {
            println(it.toString())
            driver.getSelf().findElement(it).click()
        }
    }

    fun moveReservationView() {
        driver.getSelf().findElement(nextButton).click()
    }

    fun selectedEmpty(): Boolean {
        return selectedCount == 0
    }

    private fun convertYearMonth(text: String): YearMonth {
        val yearCharIndex = text.indexOf("年")
        val monthCharIndex = text.indexOf("月")

        val year: String = text.substring(0, yearCharIndex)
        val month: String = text.substring(yearCharIndex + 1, monthCharIndex)
        if (year.isNullOrBlank() || month.isNullOrBlank()) AssertionError("invalid year month")

        return YearMonth.of(year.toInt(), month.toInt())
    }

    private fun isNotHoliday(week: DayOfWeek): Boolean = week != DayOfWeek.SATURDAY && week != DayOfWeek.SUNDAY
}