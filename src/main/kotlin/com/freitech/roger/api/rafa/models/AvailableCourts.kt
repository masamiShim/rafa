package com.freitech.roger.api.rafa.models

import java.time.LocalDate

class AvailableCourts() {
    private lateinit var date: LocalDate
    private var availableCourts: MutableMap<String, List<String>> = HashMap()

    constructor(availableCourts: List<AvailableCourt>) : this() {
        date = availableCourts[0].date
        availableCourts.forEach {
            val list: MutableList<String> = if (this.availableCourts.containsKey(it.court)) {
                this.availableCourts[it.court]!!.toMutableList()
            } else {
                mutableListOf()
            }

            list.add(it.availableTimes)
            this.availableCourts[it.court] = list
        }
    }
}