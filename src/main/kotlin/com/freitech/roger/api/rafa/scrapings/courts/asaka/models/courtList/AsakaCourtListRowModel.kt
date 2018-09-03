package com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList

import org.openqa.selenium.By

class AsakaCourtListRowModel {
    fun getAvailableSymbolPath(yyyyMMddString: String): By = By.xpath("//*[@id=\"dlRepeat_ctl00_tpItem_dgTable_ctl02_b${yyyyMMddString}\"]")
}