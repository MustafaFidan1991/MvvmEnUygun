package com.enuygun

import android.app.Application
import com.enuygun.injection.component.DaggerViewModelInjector
import com.enuygun.injection.component.ViewModelInjector
import com.enuygun.injection.module.ContextModule
import com.enuygun.injection.module.NetworkModule
import com.enuygun.injection.module.RoomModule

class EnUygunApplication :  Application() {

    companion object {
        lateinit var appComponent: ViewModelInjector
    }


    override fun onCreate() {
        super.onCreate()





        inject()

    }

    fun inject(){
        appComponent = DaggerViewModelInjector
                .builder()
                .contextModule(ContextModule(this))
                .networkModule(NetworkModule)
                .build()
    }





}