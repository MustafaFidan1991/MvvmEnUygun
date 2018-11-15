package com.enuygun.model

import com.enuygun.base.BaseModel

data class Baggage (val paxType : String, val allowance : Int, val unit : String) : BaseModel()