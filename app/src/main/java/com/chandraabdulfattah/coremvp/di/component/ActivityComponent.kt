package com.chandraabdulfattah.coremvp.di.component

import com.chandraabdulfattah.coremvp.di.PerActivity
import com.chandraabdulfattah.coremvp.di.module.ActivityModule
import com.chandraabdulfattah.coremvp.ui.features.main.MainActivity
import dagger.Component

/**
 * Created by bezzo on 26/09/17.
 */

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}
