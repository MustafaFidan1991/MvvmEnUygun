package com.enuygun.ui.main

import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import com.enuygun.BR
import com.enuygun.R
import com.enuygun.base.BaseViewModel
import com.enuygun.data.repository.FlightRepository
import com.enuygun.data.room.flightdatasource.FlightEntity
import com.enuygun.model.*
import com.enuygun.utils.ResourceProvider
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    val originDestinationCode = MutableLiveData<String>()

    val selectedDate = MutableLiveData<String>()

    val title = MutableLiveData<String>()

    @Inject
    lateinit var resourceProvider : ResourceProvider

    @Inject
    lateinit var flightRepository: FlightRepository

    lateinit var airports : List<Airport>

    lateinit var airlines : List<Airline>

    lateinit var departureFlights: List<Flight>

    lateinit var returnFlights : List<Flight>

    val flightItemWrapperListLiveData = MutableLiveData<List<FlightItemWrapper>>()

    fun getFlights(){
        compositeDisposable.add(flightRepository.
                getFlights()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {

                    loadingStatus.value = true

                }
                .doOnTerminate {
                    Handler().postDelayed({
                        loadingStatus.value = false
                    },3000)

                }
                .subscribe(
                        {
                            response  ->

                            setValues(response)

                            setDepartureInfos()

                            saveDepartureFlightsToRoom(){
                                saveReturnFlightsToRoom(){
                                    createItemWrapperList(departureFlights)
                                }
                            }

                        },
                        {

                        }
                ))
    }


    private fun setDepartureInfos(){

        title.value = resourceProvider.getString(R.string.select_departure_flight)

        originDestinationCode.value = "ISTA - ADA"

        selectedDate.value = departureFlights.first().segments.arrivalDateTime.date
    }

    private fun setReturnInfos(){

        title.value = resourceProvider.getString(R.string.select_return_flight)

        originDestinationCode.value = "ADA - IST"

        selectedDate.value = returnFlights.first().segments.arrivalDateTime.date
    }



    private fun setValues(response: Response){
        airports = response.airports
        airlines = response.airlines
        departureFlights = response.flights.departure
        returnFlights = response.flights.returnFlights

    }


    private fun saveDepartureFlightsToRoom(onSaveComplete : ()->Unit){


        Completable.fromAction {

            flightRepository.saveFlights(createFlightEntityList(departureFlights))
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {

                    override fun onSubscribe(@NonNull d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        onSaveComplete()
                    }

                    override fun onError(@NonNull e: Throwable) {
                        e.printStackTrace()
                    }

                })


    }


    private fun saveReturnFlightsToRoom(onSaveComplete : ()->Unit){
        Completable.fromAction {

            flightRepository.saveFlights(createFlightEntityList(returnFlights))

        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {

                    override fun onSubscribe(@NonNull d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {
                        onSaveComplete()
                    }

                    override fun onError(@NonNull e: Throwable) {
                        e.printStackTrace()
                    }

                })

    }

    private fun createFlightEntityList(flights : List<Flight>) = flights.map {
        flight->
        FlightEntity(
                0,
                getAirlineName(airlines,flight.segments.airlineCode)!!,
                flight.segments.origin,
                flight.segments.destination,
                getAirportName(airports,flight.segments.origin)!!,
                getAirportName(airports,flight.segments.destination)!!,
                flight.segments.departureDateTime.time,
                flight.segments.arrivalDateTime.time,
                calculateDuration(flight.infos.duration),
                getBaggageInfoString(flight.infos.baggageInfo),
                flight.price.total.toString(),
                getCurrency(flight.price.currency),
                flight.segments.availableSeatCount.toString(),
                FlightType.DEPARTURE.type
        )


    }

    private fun createItemWrapperList(flights : List<Flight>){
        flightItemWrapperListLiveData.value = flights.map {
            flight->
            FlightItemWrapper(
                    getAirlineName(airlines,flight.segments.airlineCode)!!,
                    flight.segments.origin,
                    flight.segments.destination,
                    getAirportName(airports,flight.segments.origin)!!,
                    getAirportName(airports,flight.segments.destination)!!,
                    flight.segments.departureDateTime.time,
                    flight.segments.arrivalDateTime.time,
                    calculateDuration(flight.infos.duration),
                    getBaggageInfoString(flight.infos.baggageInfo),
                    flight.price.total.toString(),
                    getCurrency(flight.price.currency),
                    flight.segments.availableSeatCount
            )
        }


    }


    fun getCurrency(currencyCode : String) : String{
        val currency = CurrencyType.fromString(currencyCode)
        when(currency){
            CurrencyType.TRY->{
                return resourceProvider.getString(R.string.currency_tr)
            }
        }
        return ""
    }


    fun getBaggageInfoString(baggageInfo: BaggageInfo) =
            resourceProvider.getString(R.string.baggage_person,baggageInfo.firstBaggageCollection[0].allowance.toString()+" "+baggageInfo.firstBaggageCollection[0].unit)


    private fun calculateDuration(duration: Duration) : String{
        var durationStr = ""
        if(duration.day > 0) durationStr+="${resourceProvider.getString(R.string.duration_day,duration.day)} "
        if(duration.hour > 0) durationStr+="${resourceProvider.getString(R.string.duration_hour,duration.hour)} "
        if(duration.minute > 0) durationStr+="${resourceProvider.getString(R.string.duration_minute,duration.hour) } "
        return durationStr
    }


    private fun getAirportName(airports : List<Airport>, airportCode : String) : String?{
        airports.forEach {
            airport->
            if(airport.code == airportCode) return airport.name
        }
        return null
    }


    private fun getAirlineName(airlines : List<Airline>, airlineCode : String) : String?{
         airlines.forEach {
             airline->
             if(airline.code == airlineCode) return airline.name
        }
        return null
    }

    fun getReturnedFlights() {
        createItemWrapperList(returnFlights)
        setReturnInfos()
    }


}