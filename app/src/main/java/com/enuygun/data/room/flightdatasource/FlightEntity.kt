package com.enuygun.data.room.flightdatasource

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.enuygun.base.BaseModel
import com.enuygun.data.room.db.RoomConstants


@Entity(tableName = RoomConstants.TABLE_FLIGHT)
data class FlightEntity (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var uid: Int = 0,

        var airlineName : String ?=null,
        
        var originAirportCode : String?=null,
        
        var destinationAirportCode : String?=null,
        
        var originAirportName : String?=null,
        
        var destinationAirportName : String?=null,
        
        var departureTime : String?=null,
        
        var arrivalTime : String?=null,
        
        
        var duration : String?=null,
        
        var baggageInfo : String?=null,
        
        var totalPrice : String?=null,
        
        var currency : String?=null,
        
        var availableSeatCount : String?=null,
        
        var flightType : Int = 0
): BaseModel()
