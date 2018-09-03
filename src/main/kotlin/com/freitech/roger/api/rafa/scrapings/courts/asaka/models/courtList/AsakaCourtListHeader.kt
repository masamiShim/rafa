package com.freitech.roger.api.rafa.scrapings.courts.asaka.models.courtList

import org.openqa.selenium.By

/**
 * コート毎に
 */
class AsakaCourtListHeader(val row: Int) {
    // 多分年月
    //*[@id="dlRepeat_ctl00_tpItem_dgTable"]/tbody/tr[1]/td[1]/text()[1]
    fun getYearMonthPath(): By = By.xpath("//*[@id=\"dlRepeat_ctl00_tpItem_dgTable\"]/tbody/tr[$row]/td[1]/text()[1]")

    // 最初の日付 25 ... 31 ... 1.. となるので日付の位が下がったら年月を上げる(多分曜日は取れる)
    //*[@id="dlRepeat_ctl00_tpItem_dgTable"]/tbody/tr[1]/td[3]/text()[1]
    fun getDatePath(col: Int): By {
        if (col < 3) AssertionError("col needs over 3")
        return By.xpath("//*[@id=\"dlRepeat_ctl00_tpItem_dgTable\"]/tbody/tr[1]/td[$col]/")
    }
//*[@id="dlRepeat_ctl00_tpItem_dgTable_ctl02_b20180825"]
}