package com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList

class   BenzaitenReservation : CourtReservation {
    override val timeSpan: Int = 2
    override val timeSlotMap: Map<Int, String> = mapOf(Pair(0, "8:30")
            , Pair(1, "10:30")
            , Pair(2, "12:30")
            , Pair(3, "14:30"))

    override val courtMap: Map<Int, String> = mapOf(Pair(2, "A")
            , Pair(3, "B"))



}