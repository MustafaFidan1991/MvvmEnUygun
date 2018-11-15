package com.enuygun.data.repository

import android.arch.persistence.room.Insert
import com.enuygun.data.room.flightdatasource.FlightEntity
import com.enuygun.model.Flight
import com.enuygun.model.Response
import io.reactivex.Observable
import io.reactivex.Single

interface IFlightRepository {

    fun getFlights() : Observable<Response>

    @Insert
    fun saveFlights(flights : List<FlightEntity>)

}