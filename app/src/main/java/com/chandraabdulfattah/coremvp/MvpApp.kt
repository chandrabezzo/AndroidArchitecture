package com.chandraabdulfattah.coremvp

import android.app.Application
import android.support.compat.BuildConfig
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.chandraabdulfattah.coremvp.di.component.ApplicationComponent
import com.chandraabdulfattah.coremvp.di.component.DaggerApplicationComponent
import com.chandraabdulfattah.coremvp.di.module.ApplicationModule
import com.chandraabdulfattah.coremvp.util.AppLogger
import com.orhanobut.hawk.Hawk

/**
 * Created by bezzo on 11/01/18.
 */
class MvpApp : Application() {
    // Needed to replace the component with a test specific one
    var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()

        component!!.inject(this)

        AppLogger.init()
        Hawk.init(this).build()

        AndroidNetworking.initialize(applicationContext)
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)
        }
    }
}