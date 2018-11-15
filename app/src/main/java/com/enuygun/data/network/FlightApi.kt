package com.enuygun.data.network

import com.enuygun.model.Response
import io.reactivex.Observable
import retrofit2.http.GET

interface FlightApi {

    @GET("5af008692f00006600739d28")
    fun getFlights(): Observable<Response>

}