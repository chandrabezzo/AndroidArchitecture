package com.chandraabdulfattah.coremvp.ui.base

import android.content.Context
import android.os.Bundle

/**
 * Created by bezzo on 21/12/17.
 */

interface BaseActivityView : BaseView {

    fun getContext(): Context?

    fun displayHome()

    fun setActionBarTitle(title: String)

    fun showSnackBar(message: String, duration: Int)

    fun gotoDialog(dialogClass: Class<*>, data: Bundle?)
}