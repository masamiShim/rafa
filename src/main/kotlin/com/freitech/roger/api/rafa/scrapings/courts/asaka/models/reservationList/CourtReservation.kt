package com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList

import com.freitech.roger.api.rafa.models.AvailableCourt
import java.time.LocalDate

interface CourtReservation {
    val timeSpan : Int
    val timeSlotMap: Map<Int, String>
    val courtMap: Map<Int, String>

    fun getTimeSlotLength(): Int {
        return timeSlotMap.size
    }

    fun getTimeSlotIndex(): Int {
        return timeSlotMap.size - 1
    }

    fun getPath(row: String, col: String): String {
        return "//*[@id=\"dlRepeat_ctl00_tpItem_dgTable_ctl0${row}_l$col\"]"
    }

    fun createAvailableCourt(date: LocalDate, courtNum: Int, timeSlot: Int): AvailableCourt {
        return AvailableCourt(date, courtMap[courtNum]!!, timeSlotMap[timeSlot]!!)
    }

}