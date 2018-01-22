package com.chandraabdulfattah.coremvp.adapter.spinner

/**
 * Created by bezzo on 11/01/18.
 */
interface SpinnerContract<in V : Any> {

    fun update(values : V)

    fun clear()
}