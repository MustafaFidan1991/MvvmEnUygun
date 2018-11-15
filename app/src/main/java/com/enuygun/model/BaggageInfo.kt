package com.enuygun.model

import com.enuygun.base.BaseModel

data class BaggageInfo (val carryOn: CarryOn,val firstBaggageCollection : List<Baggage>) : BaseModel()