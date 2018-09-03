package com.freitech.roger.api.rafa.scrapings

import com.freitech.roger.api.rafa.RafaApplicationTests
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.courtList.AvailableDateModel
import com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList.AobadaiReservation
import com.freitech.roger.api.rafa.scrapings.courts.asaka.pages.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.stream.Collectors

@SpringBootTest(classes = [RafaApplicationTests::class])
class ScrapingBaseTest {

    val entryPageUrl = "https://www2.pf489.com/asaka/web/Wg_ModeSelect.aspx"
    val courtSelectUrl = "https://www2.pf489.com/asaka/Web/Wg_ShisetsuIchiran.aspx"
    val termSelectUrl = "https://www2.pf489.com/asaka/Web/Wg_NichijiSentaku.aspx"
    val courtListViewUrl = "https://www2.pf489.com/asaka/Web/Wg_ShisetsubetsuAkiJoukyou.aspx"
    val reservationCourtListViewUrl = "https://www2.pf489.com/asaka/Web/Wg_ShisetsubetsuAkiJoukyou.aspx"

    @Autowired
    val chromeDriver: ChromeDriver = ChromeDriver()

    @Before
    fun setUp() {

    }

    @Test
    fun entryPageAvailableTest() {
        var isRequested = try {
            chromeDriver.httpRequestGet(entryPageUrl)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        Assert.assertTrue(isRequested)
    }


    @Test
    fun courtSelectPageTransferTest() {
        var isTransfered = try {
            chromeDriver.httpRequestGet(entryPageUrl)
            ReservationEntryPage(chromeDriver).transferCourtListPage()
            chromeDriver.getSelf().currentUrl.equals(courtSelectUrl)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        Assert.assertTrue(isTransfered)
    }

    @Test
    fun ViewTermSelectPageTransfereTest() {
        var isTransfered = try {
            chromeDriver.httpRequestGet(entryPageUrl)
            ReservationEntryPage(chromeDriver).transferCourtListPage()
            CourtListPage(chromeDriver).allSelectedAndTransferNextPage()
            chromeDriver.getSelf().currentUrl.equals(termSelectUrl)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        Assert.assertTrue(isTransfered)
    }

    @Test
    fun CourtListViewPageTransferTest() {
        var isTransfered = try {
            chromeDriver.httpRequestGet(entryPageUrl)
            ReservationEntryPage(chromeDriver).transferCourtListPage()
            CourtListPage(chromeDriver).allSelectedAndTransferNextPage()
            val now = LocalDate.now()
            ViewTermSelectPage(chromeDriver, now.year, now.monthValue, now.dayOfMonth).inputDateAndViewHolidayAndTransferCourtListPage()
            chromeDriver.getSelf().currentUrl.equals(courtListViewUrl)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        Assert.assertTrue(isTransfered)
    }

    @Test
    fun ReservationListViewPageTransferTest() {
        var isTransfered = try {
            chromeDriver.httpRequestGet(entryPageUrl)
            ReservationEntryPage(chromeDriver).transferCourtListPage()
            CourtListPage(chromeDriver).allSelectedAndTransferNextPage()
            val now = LocalDate.now()
            ViewTermSelectPage(chromeDriver, now.year, now.monthValue, now.dayOfMonth).inputDateAndViewHolidayAndTransferCourtListPage()
            val courtListViewPage = CourtListViewPage(chromeDriver, now)
            val availableMap = courtListViewPage.getAvailableMap()
            if (availableMap.isNotEmpty()) {
                courtListViewPage.clickAll(availableMap.values.stream().map { it -> it.linkPath }.collect(Collectors.toSet()) as Set<By>)
                courtListViewPage.moveReservationView()
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        Assert.assertTrue(isTransfered)
    }


    @Test
    fun SelectReservationTest() {
        var isTransfered = try {
            //　初期ページへ
            chromeDriver.httpRequestGet(entryPageUrl)
            //　コート選択ページへ
            ReservationEntryPage(chromeDriver).transferCourtListPage()
            //　コートを全選択
            CourtListPage(chromeDriver).allSelectedAndTransferNextPage()
            val now = LocalDate.now()
            //　現在日以降で土日のコートを選択する
            ViewTermSelectPage(chromeDriver, now.year, now.monthValue, now.dayOfMonth).inputDateAndViewHolidayAndTransferCourtListPage()
            val courtListViewPage = CourtListViewPage(chromeDriver, now)
            val availableMap = courtListViewPage.getAvailableMap()
            if (availableMap.isNotEmpty()) {
                courtListViewPage.clickAll(availableMap.values.stream().map { it -> it.linkPath }.collect(Collectors.toSet()) as Set<By>)
                courtListViewPage.moveReservationView()
                //　現在日以降で土日のコートを選択する
                val reserve: ReferenceAvailablePage = ReferenceAvailablePage(chromeDriver)
                val availableTimeMap = reserve.findAvailableCourt(AobadaiReservation())
                print(availableTimeMap.toString())
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        Assert.assertTrue(isTransfered)
    }

    @After
    fun tearDown() {
        //  chromeDriver.driver.quit()
    }
}