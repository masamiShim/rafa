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
@Api(tags = ["/isHealthy"])
class IsHealthyController {

    @GetMapping("/isHealthy")
    @ApiOperation(value ="Apiサーバーが生きているか確認する")
    fun index(): ResponseEntity<String> {
        return ResponseEntity.ok("success")
    }
}