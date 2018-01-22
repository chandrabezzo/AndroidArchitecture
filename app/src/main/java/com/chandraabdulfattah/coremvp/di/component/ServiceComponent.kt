package com.chandraabdulfattah.coremvp.di.component

import com.chandraabdulfattah.coremvp.di.PerService
import com.chandraabdulfattah.coremvp.di.module.ServiceModule
import com.chandraabdulfattah.coremvp.service.SyncService
import dagger.Component

/**
 * Created by bezzo on 26/09/17.
 */

@PerService
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = [(ServiceModule::class)])
interface ServiceComponent {

    fun inject(service: SyncService)
}