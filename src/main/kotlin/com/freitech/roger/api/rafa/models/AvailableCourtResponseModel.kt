package com.freitech.roger.api.rafa.models


class AvailableCourtResponseModel {
    lateinit var availableCourts: AvailableCourts
    var version: Int = 0
    lateinit var status: String

    constructor(availableCourtList: List<AvailableCourt>) {
        availableCourts = AvailableCourts(availableCourtList)
        version = 0
        status = "200"
    }
}