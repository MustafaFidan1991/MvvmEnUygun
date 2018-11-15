package com.enuygun.injection.module

import com.enuygun.data.network.FlightApi
import com.enuygun.utils.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
@Suppress("unused")
object NetworkModule {




    @Provides
    @Reusable
    @JvmStatic
    internal fun provideFlightApi(retrofit: Retrofit): FlightApi {
        return retrofit.create(FlightApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGson(): Gson {
        return Gson()
    }


}