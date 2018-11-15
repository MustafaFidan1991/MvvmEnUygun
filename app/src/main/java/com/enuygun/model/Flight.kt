package com.enuygun.model

import com.enuygun.base.BaseModel

data class Flight(val price : Price, val infos : Infos,val segments : Segments) : BaseModel()