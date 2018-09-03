package com.freitech.roger.api.rafa.scrapings.courts.asaka.pages

import com.freitech.roger.api.rafa.scrapings.ChromeDriver
import org.openqa.selenium.By

/**
 * 施設の空き状況紹介ページを開く際の以下を決定するページ
 * <ul>
 *     <li>表示開始日</li>
 *     <li>期間</li>
 *     <li>表示する曜日</li>
 * </ul>
 */
class ViewTermSelectPage(
        val driver: ChromeDriver
        , val year: Int
        , val month: Int
        , val day: Int) {

    // input view Term From
    fun getYearInput(): By = By.xpath("//*[@id=\"txtYear\"]")
    fun getMonthInput(): By = By.xpath("//*[@id=\"txtMonth\"]")
    fun getDayInput(): By = By.xpath("//*[@id=\"txtDay\"]")
    // select view Term
    // 一応1カ月と全日
    fun getSelAnnualMonth(): By = By.xpath("//*[@id=\"rbtnMonth\"]")

    fun getDisplayDayAtAll(): By = By.xpath("//*[@id=\"rbtnAllday\"]")
    fun getNextButtonId(): By = By.xpath("//*[@id=\"ucPCFooter_btnForward\"]")

    // select display week
    fun getWeekMON(): By = By.xpath("//*[@id=\"chkMon\"]")
    fun getWeekTUE(): By = By.xpath("//*[@id=\"chkTue\"]")
    fun getWeekWED(): By = By.xpath("//*[@id=\"chkWed\"]")
    fun getWeekTHU(): By = By.xpath("//*[@id=\"chkThu\"]")
    fun getWeekFRI(): By = By.xpath("//*[@id=\"chkFri\"]")
    fun getWeekSAT(): By = By.xpath("//*[@id=\"chkSat\"]")
    fun getWeekSUN(): By = By.xpath("//*[@id=\"chkSun\"]")
    fun getWeekHOL(): By = By.xpath("//*[@id=\"chkHol\"]")



    fun inputStartDate() {
        driver.getSelf().findElement(getYearInput()).clear()
        driver.getSelf().findElement(getYearInput()).sendKeys(year.toString())
        driver.getSelf().findElement(getMonthInput()).clear()
        driver.getSelf().findElement(getMonthInput()).sendKeys(month.toString())
        driver.getSelf().findElement(getDayInput()).clear()
        driver.getSelf().findElement(getDayInput()).sendKeys(day.toString())
    }

    fun selectTermAnnualMonth() {
        driver.getSelf().findElement(getSelAnnualMonth()).click()
    }

    fun selectDayTermAtAll() {
        driver.getSelf().findElement(getDisplayDayAtAll()).click()
    }

    fun selectWeekHoliday() {
        driver.getSelf().findElement(getWeekSAT()).click()
        driver.getSelf().findElement(getWeekSUN()).click()
        driver.getSelf().findElement(getWeekHOL()).click()
    }
    fun selectWeekday() {
        driver.getSelf().findElement(getWeekMON()).click()
        driver.getSelf().findElement(getWeekTUE()).click()
        driver.getSelf().findElement(getWeekWED()).click()
        driver.getSelf().findElement(getWeekTHU()).click()
        driver.getSelf().findElement(getWeekFRI()).click()
    }
    fun transferCourtListView() {
        driver.getSelf().findElement(getNextButtonId()).click()
    }

    fun inputDateAndViewHolidayAndTransferCourtListPage() {
        this.inputStartDate()
        this.selectTermAnnualMonth()
        this.selectDayTermAtAll()
        //this.selectWeekday()
        this.selectWeekHoliday()
        this.transferCourtListView()
    }
}