package com.enuygun.data.room.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.enuygun.data.room.flightdatasource.FlightDao
import com.enuygun.data.room.flightdatasource.FlightEntity

@Database(
        entities = arrayOf(FlightEntity::class),
        version = 1)
abstract class RoomDataSource : RoomDatabase(){

    abstract fun flightDao(): FlightDao

    companion object {

        fun buildPersistentFlight(context: Context): RoomDataSource = Room.databaseBuilder(
                context.applicationContext,
                RoomDataSource::class.java,
                RoomConstants.DATABASE_EN_UYGUN
        ).build()
    }

}
