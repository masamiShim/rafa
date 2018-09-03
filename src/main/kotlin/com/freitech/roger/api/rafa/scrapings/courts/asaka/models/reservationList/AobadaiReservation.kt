package com.freitech.roger.api.rafa.scrapings.courts.asaka.models.reservationList

class AobadaiReservation : CourtReservation {
    override val timeSpan: Int = 2
    override val timeSlotMap: Map<Int, String> = mapOf(Pair(0, "6:30")
            , Pair(1, "8:30")
            , Pair(2, "10:30")
            , Pair(3, "12:30")
            , Pair(4, "14:30")
            , Pair(5, "17:00")
            , Pair(6, "19:00"))

    override val courtMap: Map<Int, String> = mapOf(Pair(2, "A")
            , Pair(3, "B")
            , Pair(4, "C")
            , Pair(5, "D")
            , Pair(6, "E"))



}