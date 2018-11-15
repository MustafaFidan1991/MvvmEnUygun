package com.enuygun.model

import com.enuygun.base.BaseModel

data class FlightItemWrapper (
        val airlineName : String,
        val originAirportCode : String,
        val destinationAirportCode : String,
        val originAirportName : String,
        val destinationAirportName : String,
        val departureTime : String,
        val arrivalTime : String,
        val duration : String,
        val baggageInfo : String,
        val totalPrice : String,
        val currency : String,
        val availableSeatCount : Int
) : BaseModel()