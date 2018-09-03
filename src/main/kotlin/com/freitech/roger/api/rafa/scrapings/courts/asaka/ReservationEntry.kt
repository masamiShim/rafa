package com.freitech.roger.api.rafa.scrapings.courts.asaka

import org.openqa.selenium.By

class ReservationEntry {
    private val tennisCourt = "//*[@id=\"dlSSCategory_ctl01_btnSSCategory\"]"
    //*[@id="dlRepeat_ctl00_tpItem_dgTable_ctl02_l0"]
    //*[@id="dlRepeat_ctl00_tpItem_dgTable_ctl02_l1"]
    private val loginButton = "//*[@id=\"rbtnLogin\"]"

    fun getTennisCourt(): By = By.xpath(tennisCourt)
    fun getLoginButton(): By = By.xpath(loginButton)



}