package ru.blackmirrror.fooddelivery.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.blackmirrror.fooddelivery.app.di.appModule
import ru.blackmirrror.fooddelivery.app.di.dataModule
import ru.blackmirrror.fooddelivery.app.di.domainModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, domainModule, appModule)
        }
    }
}