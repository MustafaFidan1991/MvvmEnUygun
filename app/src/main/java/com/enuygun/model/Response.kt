package com.enuygun.model

import com.enuygun.base.BaseModel

data class Response(val airports : List<Airport>,val airlines : List<Airline>,val flights : Flights) : BaseModel()