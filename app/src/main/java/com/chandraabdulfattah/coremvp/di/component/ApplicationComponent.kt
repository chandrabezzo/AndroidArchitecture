package com.chandraabdulfattah.coremvp.di.component

import android.app.Application
import android.content.Context
import com.chandraabdulfattah.coremvp.MvpApp
import com.chandraabdulfattah.coremvp.data.DataManagerContract
import com.chandraabdulfattah.coremvp.di.ApplicationContext
import com.chandraabdulfattah.coremvp.di.module.ApplicationModule
import com.chandraabdulfattah.coremvp.service.MessagingInstanceIDService
import com.chandraabdulfattah.coremvp.service.MessagingService
import com.mybarber18.partner.service.UpdateLocationService
import dagger.Component
import javax.inject.Singleton

/**
 * Created by bezzo on 26/09/17.
 */

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    val dataManager: DataManagerContract

    fun inject(app: MvpApp)

    fun inject(messagingInstanceIDService: MessagingInstanceIDService)

    fun inject(messagingService: MessagingService)

    fun inject(updateLocationService: UpdateLocationService)

    @ApplicationContext
    fun context(): Context

    fun application(): Application
}
