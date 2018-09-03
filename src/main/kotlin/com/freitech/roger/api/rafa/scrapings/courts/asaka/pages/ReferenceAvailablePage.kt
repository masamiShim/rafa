package com.freitech.roger.api.rafa.scrapings.courts.asaka.pages

import com.freitech.roger.api.rafa.models.AvailableCourt
import com.freitech.roger.api.rafa.scrapings.ChromeDriver
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList.CourtReservation
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import java.time.LocalDate

class ReferenceAvailablePage(private val driver: ChromeDriver) {
    val datePath = By.xpath("//*[@id=\"dlRepeat_ctl00_tpItem_dgTable\"]/tbody/tr[1]/td[1]")


    /**
     * 利用可能なコートを探す
     * @param court
     * @return List<AvailableCourt>
     */
    fun findAvailableCourt(court: CourtReservation): List<AvailableCourt> {

        var availableCourtList: MutableList<AvailableCourt> = ArrayList()
        // 利用可能なコートの日付を取得する
        val date = getAvailableDate()

        court.courtMap.keys.forEach { row ->
            court.timeSlotMap.keys.forEach { col ->
                val path: By = By.xpath(court.getPath(row.toString(), col.toString()))
                try {
                    val symbol: String = driver.getSelf().findElement(path).text
                    // 見つかったらそれはそれでよし
                    if (isAvailable(symbol)) {
                        availableCourtList.add(court.createAvailableCourt(date, row, col))
                    }
                } catch (nse: NoSuchElementException) {
                    // 見つからなかったら予約可能と判定
                    System.out.println("NoSuchElementException $row $col")
                    availableCourtList.add(court.createAvailableCourt(date, row, col))
                }
            }
        }
        return availableCourtList
    }

    /**
     * 利用可能な日付を返す
     */
    private fun getAvailableDate(): LocalDate {
        val jDate = driver.getSelf().findElement(datePath).text.split("\n")[0]

        val dateString = jDate.replace("年", "-")
                .replace("月", "-")
                .replace("日", "").split("-")

        return LocalDate.of(dateString[0].toInt(), dateString[1].toInt(), dateString[2].toInt())
    }

    /**
     * 利用可能なコート判定(現状使われてないけど・・・)
     */
    private fun isAvailable(symbol: String): Boolean {
        return symbol.contains("△") || symbol.contains("〇")
    }

    // TODO: 後ほど
    fun reserveCourt(court: CourtReservation) {

        court.courtMap.forEach { row ->
            IntRange(0, court.getTimeSlotLength()).forEach { col ->
                val path: By = By.xpath(court.getPath(row.toString(), col.toString()))
                val symbol: String = driver.getSelf().findElement(path).text

                if (isAvailable(symbol)) {
                    driver.getSelf().findElement(path).click()
                }
            }
        }
    }

}