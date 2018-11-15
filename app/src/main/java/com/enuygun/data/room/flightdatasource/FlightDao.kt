package com.enuygun.data.room.flightdatasource

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert

@Dao
interface FlightDao {

    @Insert
    fun insertAll(infos: List<FlightEntity>)

}