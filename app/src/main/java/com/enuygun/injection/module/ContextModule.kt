package com.enuygun.injection.module

import android.app.Application
import android.content.Context
import com.enuygun.EnUygunApplication
import com.enuygun.utils.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
@Suppress("unused")
class ContextModule(private val application: EnUygunApplication) {

    @Provides @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides @Singleton
    fun provideApplication(): Application {
        return application
    }


    @Provides @Singleton
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }

}