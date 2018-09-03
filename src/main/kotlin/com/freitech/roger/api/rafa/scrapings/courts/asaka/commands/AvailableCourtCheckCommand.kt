package com.freitech.roger.api.rafa.scrapings.courts.asaka.commands

import com.freitech.roger.api.rafa.models.AvailableCourt
import com.freitech.roger.api.rafa.scrapings.ChromeDriver
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList.AobadaiReservation
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList.BenzaitenReservation
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList.TakinoneReservation
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList.UchimagiReservation
import com.freitech.roger.api.rafa.scrapings.courts.asaka.pages.*
import org.openqa.selenium.By
import java.time.LocalDate
import java.util.stream.Collectors

class AvailableCourtCheckCommand(
        private val driver: ChromeDriver) {

    fun checkAvailableCourt(year: Int, month: Int, day: Int): List<AvailableCourt> {
        // 初期ページへ移動
        driver.getSelf().get("https://www2.pf489.com/asaka/web/Wg_ModeSelect.aspx")

        // テニスコート選択ページへ移動
        ReservationEntryPage(driver).transferCourtListPage()

        //　表示期間設定ページへ遷移
        CourtListPage(driver).selectAobadai()
                .transferViewTermSelectPage()

        //　コート空き状況参照ページへ遷移
        ViewTermSelectPage(driver, year, month, day).inputDateAndViewHolidayAndTransferCourtListPage()

        val page = CourtListViewPage(driver, LocalDate.now())
        // これはページに持たせていいや
        val map = page.getAvailableMap()

        if (page.selectedEmpty()) {
            return emptyList()
        }
        page.clickAll(map.values.stream().map { it -> it.linkPath }.collect(Collectors.toSet()) as Set<By>)
        page.moveReservationView()

        // 青葉台コートを取得
        return ReferenceAvailablePage(driver).findAvailableCourt(AobadaiReservation())


    }

    fun checkAobadaiCourt(year: Int, month: Int, day: Int): List<AvailableCourt> {
        // 初期ページへ移動
        driver.getSelf().get("https://www2.pf489.com/asaka/web/Wg_ModeSelect.aspx")

        // テニスコート選択ページへ移動
        ReservationEntryPage(driver).transferCourtListPage()

        //　表示期間設定ページへ遷移
        CourtListPage(driver).selectAobadai()
                .transferViewTermSelectPage()

        //　コート空き状況参照ページへ遷移
        ViewTermSelectPage(driver, year, month, day).inputDateAndViewHolidayAndTransferCourtListPage()

        val page = CourtListViewPage(driver, LocalDate.now())
        // これはページに持たせていいや
        val map = page.getAvailableMap()

        if (page.selectedEmpty()) {
            return emptyList()
        }
        page.clickAll(map.values.stream().map { it -> it.linkPath }.collect(Collectors.toSet()) as Set<By>)
        page.moveReservationView()

        // 青葉台コートを取得
        return ReferenceAvailablePage(driver).findAvailableCourt(AobadaiReservation())


    }


    /**
     * 弁財天テニスコートの選択
     */
    fun checkBenzaitenCourt(year: Int, month: Int, day: Int): List<AvailableCourt> {
        // 初期ページへ移動
        driver.getSelf().get("https://www2.pf489.com/asaka/web/Wg_ModeSelect.aspx")

        // テニスコート選択ページへ移動
        ReservationEntryPage(driver).transferCourtListPage()

        //　表示期間設定ページへ遷移
        CourtListPage(driver).selectBenzaiten()
                .transferViewTermSelectPage()

        //　コート空き状況参照ページへ遷移
        ViewTermSelectPage(driver, year, month, day).inputDateAndViewHolidayAndTransferCourtListPage()

        val page = CourtListViewPage(driver, LocalDate.now())
        // これはページに持たせていいや
        val map = page.getAvailableMap()

        if (page.selectedEmpty()) {
            return emptyList()
        }
        page.clickAll(map.values.stream().map { it -> it.linkPath }.collect(Collectors.toSet()) as Set<By>)
        page.moveReservationView()

        // 弁財天のコートを検索
        return ReferenceAvailablePage(driver).findAvailableCourt(BenzaitenReservation())

    }

    /**
     * 内間木テニスコートの選択
     */
    fun checkUchimagiCourt(year: Int, month: Int, day: Int): List<AvailableCourt> {
        // 初期ページへ移動
        driver.getSelf().get("https://www2.pf489.com/asaka/web/Wg_ModeSelect.aspx")

        // テニスコート選択ページへ移動
        ReservationEntryPage(driver).transferCourtListPage()

        //　表示期間設定ページへ遷移
        CourtListPage(driver).selectUchimagi()
                .transferViewTermSelectPage()

        //　コート空き状況参照ページへ遷移
        ViewTermSelectPage(driver, year, month, day).inputDateAndViewHolidayAndTransferCourtListPage()

        val page = CourtListViewPage(driver, LocalDate.now())
        // これはページに持たせていいや
        val map = page.getAvailableMap()

        if (page.selectedEmpty()) {
            return emptyList()
        }
        page.clickAll(map.values.stream().map { it -> it.linkPath }.collect(Collectors.toSet()) as Set<By>)
        page.moveReservationView()

        // 弁財天のコートを検索
        return ReferenceAvailablePage(driver).findAvailableCourt(UchimagiReservation())
    }

    /**
     * 滝の根テニスコートの選択
     */
    fun checkTakinoneCourt(year: Int, month: Int, day: Int): List<AvailableCourt> {
        // 初期ページへ移動
        driver.getSelf().get("https://www2.pf489.com/asaka/web/Wg_ModeSelect.aspx")

        // テニスコート選択ページへ移動
        ReservationEntryPage(driver).transferCourtListPage()

        //　表示期間設定ページへ遷移
        CourtListPage(driver).selectTakinone()
                .transferViewTermSelectPage()

        //　コート空き状況参照ページへ遷移
        ViewTermSelectPage(driver, year, month, day).inputDateAndViewHolidayAndTransferCourtListPage()

        val page = CourtListViewPage(driver, LocalDate.now())
        // これはページに持たせていいや
        val map = page.getAvailableMap()

        if (page.selectedEmpty()) {
            return emptyList()
        }
        page.clickAll(map.values.stream().map { it -> it.linkPath }.collect(Collectors.toSet()) as Set<By>)
        page.moveReservationView()

        // 弁財天のコートを検索
        return ReferenceAvailablePage(driver).findAvailableCourt(TakinoneReservation())
    }



}