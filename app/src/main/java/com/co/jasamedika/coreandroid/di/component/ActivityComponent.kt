package com.co.jasamedika.coreandroid.di.component

import com.co.jasamedika.coreandroid.di.PerActivity
import com.co.jasamedika.coreandroid.di.module.ActivityModule
import com.co.jasamedika.coreandroid.features.main.MainActivity
import dagger.Component

/**
 * Created by bezzo on 26/09/17.
 */

@PerActivity
@Component(
        dependencies = [(ApplicationComponent::class)],
        modules = [(ActivityModule::class)])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}