package com.enuygun.injection.module

import android.content.Context
import com.enuygun.data.room.db.RoomDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides @Singleton fun provideRoomDataSource(context: Context) = RoomDataSource.buildPersistentFlight(context)

}
