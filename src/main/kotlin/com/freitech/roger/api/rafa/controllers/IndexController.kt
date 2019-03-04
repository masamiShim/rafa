package com.freitech.roger.api.rafa.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.freitech.roger.api.rafa.models.AvailableCourt
import com.freitech.roger.api.rafa.models.AvailableCourtResponseModel
import com.freitech.roger.api.rafa.scrapings.ChromeDriver
import com.freitech.roger.api.rafa.scrapings.courts.asaka.commands.AvailableCourtCheckCommand
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@ResponseBody
@Api(tags = ["/ref/{place}/{court}"])
class IndexController {

    @GetMapping("/ref/asaka/aobadai")
    @ApiOperation(value ="青葉台のコートの空き状況を照会する")
    fun getAobadai(): ResponseEntity<String> {
        val now: LocalDate = LocalDate.now()
        val availableCourt: List<AvailableCourt> = AvailableCourtCheckCommand(ChromeDriver())
                .checkAobadaiCourt(now.year, now.monthValue, now.dayOfMonth)
        return responseToJson(availableCourt)
    }

    @GetMapping("/ref/asaka/benzaiten")
    @ApiOperation(value ="弁財天のコートの空き状況を照会する")
    fun getBenzaiten(): ResponseEntity<String> {
        val now: LocalDate = LocalDate.now()

        val availableCourt: List<AvailableCourt> = AvailableCourtCheckCommand(ChromeDriver()).checkBenzaitenCourt(now.year, now.monthValue, now.dayOfMonth)
        return responseToJson(availableCourt)
    }

    @GetMapping("/ref/asaka/uchimagi")
    @ApiOperation(value ="内間木のコートの空き状況を照会する")
    fun getUchimagi(): ResponseEntity<String> {
        val now: LocalDate = LocalDate.now()

        val availableCourt: List<AvailableCourt> = AvailableCourtCheckCommand(ChromeDriver()).checkUchimagiCourt(now.year, now.monthValue, now.dayOfMonth)
        return responseToJson(availableCourt)
    }

    @GetMapping("/ref/asaka/takinone")
    @ApiOperation(value ="滝の根のコートの空き状況を照会する")
    fun getTakinone(): ResponseEntity<String> {
        val now: LocalDate = LocalDate.now()
        val availableCourt: List<AvailableCourt> = AvailableCourtCheckCommand(ChromeDriver()).checkTakinoneCourt(now.year, now.monthValue, now.dayOfMonth)

        return responseToJson(availableCourt)
    }

    fun responseToJson(availableCourt: List<AvailableCourt>): ResponseEntity<String> {
        if(availableCourt.isEmpty()) return ResponseEntity.ok("not found")
        val json: String = ObjectMapper().writeValueAsString(AvailableCourtResponseModel(availableCourt))
        return ResponseEntity.ok(json)

    }
}