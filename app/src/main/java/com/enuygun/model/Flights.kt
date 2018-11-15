package com.enuygun.model

import com.enuygun.base.BaseModel
import com.google.gson.annotations.SerializedName

data class Flights(val departure  : List<Flight>,@SerializedName("return")val returnFlights : List<Flight>) : BaseModel()