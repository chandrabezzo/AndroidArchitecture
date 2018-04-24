package com.chandraabdulfattah.coremvp.di.component

import com.chandraabdulfattah.coremvp.di.PerService
import com.chandraabdulfattah.coremvp.di.module.ServiceModule
import com.chandraabdulfattah.coremvp.service.MessagingInstanceIDService
import com.chandraabdulfattah.coremvp.service.MessagingService
import com.mybarber18.partner.service.UpdateLocationService
import dagger.Component

/**
 * Created by bezzo on 26/09/17.
 */

@PerService
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = [(ServiceModule::class)])
interface ServiceComponent {

    fun inject(messagingInstanceIDService: MessagingInstanceIDService)

    fun inject(messagingService: MessagingService)

    fun inject(updateLocationService: UpdateLocationService)
}