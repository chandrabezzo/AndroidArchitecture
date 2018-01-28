package com.chandraabdulfattah.coremvp.ui.features.main

import com.chandraabdulfattah.coremvp.ui.base.BasePresenterContract

/**
 * Created by bezzo on 24/01/18.
 */
interface MainPresenterContract<in V : MainViewContract> : BasePresenterContract<V> {

    fun getUserApi()

    fun getUserLokal()
}