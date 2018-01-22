package com.chandraabdulfattah.coremvp.ui.base

import com.androidnetworking.error.ANError

/**
 * Created by bezzo on 21/12/17.
 */

interface BasePresenterContract<in V : BaseView> {

    fun onAttach(mvpView: V)

    fun onDetach()

    fun handleApiError(error: ANError)

    fun setUserAsLoggedOut()

    fun logout()

    fun clearLog()
}