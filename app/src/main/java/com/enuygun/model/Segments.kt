package com.enuygun.model

import com.enuygun.base.BaseModel

data class Segments(val departureDateTime : Time,val arrivalDateTime : Time,val origin :String,val destination : String,val airlineCode : String,val availableSeatCount : Int) : BaseModel()