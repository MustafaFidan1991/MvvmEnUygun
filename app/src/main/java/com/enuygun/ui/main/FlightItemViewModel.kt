package com.enuygun.ui.main

import com.enuygun.base.BaseRecyclerItemViewModel
import com.enuygun.model.FlightItemWrapper

class FlightItemViewModel : BaseRecyclerItemViewModel<FlightItemWrapper>() {

    var airlineName : String ?=null
    
    var originDestinationAirportCode : String ?= null

    var originAirportName : String ?= null
    
    var destinationAirportName : String ?= null
    
    var departureTime : String ?= null
    
    var arrivalTime : String ?= null
    
    var duration : String ?= null
    
    var baggageInfo : String ?= null
    
    var totalPrice : String ?= null
    
    var currency : String ?= null

    var departureTimeAndOriginAirportName : String?=null

    var arrivalTimeAndDestinationAirportName : String?=null

    var imgUrl: String?=null

    var availableSeatsCount : String?=null

    var seatLayVisibility : Boolean?=null

    override fun setValues() {
        airlineName = model.airlineName
        originDestinationAirportCode = model.originAirportCode +" > "+model.destinationAirportCode
        originAirportName = model.originAirportName
        destinationAirportName = model.destinationAirportName
        departureTime = model.departureTime
        arrivalTime = model.arrivalTime
        duration = model.duration
        baggageInfo = model.baggageInfo
        totalPrice = model.totalPrice
        currency = model.currency
        departureTimeAndOriginAirportName = model.departureTime+ " "+model.originAirportName
        arrivalTimeAndDestinationAirportName = model.arrivalTime+ " "+model.destinationAirportName


        if(airlineName == "Turkish Airlines") imgUrl = "http://www.stickpng.com/assets/images/587b50f844060909aa603a7e.png"
        else if(airlineName == "Pegasus") imgUrl = "https://cdnp.flypgs.com/files/GorselArsiv/Pegasus_Airlines.jpg"



        if(model.availableSeatCount <= 2){
            seatLayVisibility = true
            availableSeatsCount = model.availableSeatCount.toString()
        }


    }

}