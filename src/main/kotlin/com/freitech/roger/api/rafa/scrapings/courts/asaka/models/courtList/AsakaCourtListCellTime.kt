package com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList

class AsakaCourtListCellTime(private val col: Int, private val row: Int) {
   private val tempCourtText: String = "//*[@id=\"dlRepeat_ctl0${col}_tpItem_dgTable_ctl0${row}_lblMen\"]/text()"

    fun getTextXpath(): String {
         return tempCourtText
     }
}