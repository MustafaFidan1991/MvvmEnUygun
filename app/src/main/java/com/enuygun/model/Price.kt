package com.enuygun.model

import com.enuygun.base.BaseModel

data class Price(val base :Int,val tax : Double,val service : Int,val total : Double,val currency : String) : BaseModel()