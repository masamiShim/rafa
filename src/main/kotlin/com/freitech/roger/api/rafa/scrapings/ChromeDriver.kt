package com.freitech.roger.api.rafa.scrapings

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class ChromeDriver {
    var driver: WebDriver

    // 10秒だとちと辛い
    val ELEMENT_FIND_TIMEOUT: Long = 1L
    val PAGE_LOAD_TIMEOUT: Long = 10L
    val SCRIPT_TIMEOUT: Long = 3L

    private var wait: WebDriverWait

    constructor() {
        System.setProperty("webdriver.chrome.driver", ".\\ext\\chromedriver.exe")
        driver = ChromeDriver(ChromeOptions().apply {
            //setBinary(java.io.File("D:\\dev\\tennisCourtApi\\rafa\\ext\\chromedriver.exe"))
            addArguments("--headless")
        })
        // とりあえず全部10秒待ち
        driver.manage().timeouts().implicitlyWait(ELEMENT_FIND_TIMEOUT, TimeUnit.SECONDS)
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
        driver.manage().timeouts().setScriptTimeout(SCRIPT_TIMEOUT, TimeUnit.SECONDS)
        wait = WebDriverWait(driver, 5)
    }

    fun httpRequestGet(url: String) {
        driver.get(url)
    }

    fun getSelf(): WebDriver {
        return driver
    }

}