package com.enuygun.model

import com.enuygun.base.BaseModel
import com.google.gson.annotations.SerializedName

data class Duration(val day : Int, val hour : Int,val minute : Int, @SerializedName("total_minutes")val totalMinutes : Int) : BaseModel()