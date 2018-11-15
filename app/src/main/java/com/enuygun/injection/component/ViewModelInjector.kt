package com.enuygun.injection.component

import com.enuygun.injection.module.*
import com.enuygun.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class),RoomModule::class])
interface ViewModelInjector {
    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun roomModule(roomModule : RoomModule) : Builder
    }
}