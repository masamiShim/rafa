package com.freitech.roger.api.rafa.scrapings.courts.asaka.pages

import com.freitech.roger.api.rafa.scrapings.ChromeDriver
import org.openqa.selenium.By

class ReservationEntryPage(private val driver: ChromeDriver) {
    private val loginButtonPath: String = "//*[@id=\"rbtnLogin\"]"
    private val courtSelButtonPath: String = "//*[@id=\"dlSSCategory_ctl01_btnSSCategory\"]"

    fun getLoginButton(): By = By.xpath(loginButtonPath)
    fun getSelectCourtButton(): By = By.xpath(courtSelButtonPath)

    fun transferLogin() {
        driver.getSelf().findElement(getLoginButton())
    }

    fun transferCourtListPage() {
        driver.getSelf().findElement(getSelectCourtButton()).click()
    }


}