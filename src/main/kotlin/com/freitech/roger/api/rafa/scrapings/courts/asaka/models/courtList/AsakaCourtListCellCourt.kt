package com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList

import com.freitech.roger.api.rafa.scrapings.courts.asaka.params.AsakaReservationCourtCol

class AsakaCourtListCellCourt(private val row: Int) {
   private val col: Int = AsakaReservationCourtCol.CourtCol.value
   private val tempCourtText: String = "//*[@id=\"dlRepeat_ctl0${col}_tpItem_dgTable_ctl0${row}_lblMen\"]/text()"

    fun getTextXpath(): String {
         return tempCourtText
     }
}