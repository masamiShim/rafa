package com.freitech.roger.api.rafa.scrapings.courts.asaka.pages

import com.freitech.roger.api.rafa.scrapings.ChromeDriver
import org.openqa.selenium.By

class CourtListPage(val driver: ChromeDriver) {
    private val aobadaiSelButton = "//*[@id=\"dgShisetsuList_ctl02_chkSelectLeft\"]"
    private val uchimagiSelButton = "//*[@id=\"dgShisetsuList_ctl02_chkSelectRight\"]"
    private val benzaitenSelButton = "//*[@id=\"dgShisetsuList_ctl03_chkSelectLeft\"]"
    private val takinoneSelButton = "//*[@id=\"dgShisetsuList_ctl03_chkSelectRight\"]"
    private val nextButton = "//*[@id=\"ucPCFooter_btnForward\"]"

    fun getAobadai(): By = By.xpath(aobadaiSelButton)
    fun getUchimagi(): By = By.xpath(uchimagiSelButton)
    fun getBenzaiten(): By = By.xpath(benzaitenSelButton)
    fun getTakinone(): By = By.xpath(takinoneSelButton)
    fun getNextButtonId(): By = By.xpath(nextButton)


    /**
     * すべてのコートを選択する
     */
    fun selectAll(): CourtListPage {
        driver.getSelf().findElement(getAobadai()).click()
       driver.getSelf().findElement(getUchimagi()).click()
        driver.getSelf().findElement(getBenzaiten()).click()
        driver.getSelf().findElement(getTakinone()).click()
        return this
    }

    fun selectAobadai(): CourtListPage {
        driver.getSelf().findElement(getAobadai()).click()
        return this
    }

    fun selectUchimagi(): CourtListPage {
        driver.getSelf().findElement(getUchimagi()).click()
        return this
    }

    fun selectBenzaiten(): CourtListPage {
        driver.getSelf().findElement(getBenzaiten()).click()
        return this
    }

    fun selectTakinone(): CourtListPage {
        driver.getSelf().findElement(getTakinone()).click()
        return this
    }

    /**
     * 表示期間選択ページへ遷移する
     */
    fun transferViewTermSelectPage() {
        driver.getSelf().findElement(getNextButtonId()).click()
    }

    fun allSelectedAndTransferNextPage(){
        this.selectAll()
        this.transferViewTermSelectPage()
    }
}