package com.enuygun.data.repository

import com.enuygun.data.network.FlightApi
import com.enuygun.data.room.db.RoomDataSource
import com.enuygun.data.room.flightdatasource.FlightDao
import com.enuygun.data.room.flightdatasource.FlightEntity
import com.enuygun.model.Flight
import com.enuygun.model.Response
import io.reactivex.Observable
import javax.inject.Inject

class FlightRepository@Inject constructor(
        private val flightApi: FlightApi,private val rooomDataSource: RoomDataSource
) : IFlightRepository{



    override fun saveFlights(flights: List<FlightEntity>) {
        rooomDataSource.flightDao().insertAll(flights)
    }


    override fun getFlights() : Observable<Response> {
        return flightApi.getFlights()
    }


}