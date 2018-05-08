package com.chandraabdulfattah.coremvp.ui.base

import android.os.Bundle
import android.support.annotation.StringRes

/**
 * Created by bezzo on 21/12/17.
 */
interface BaseView {

    fun isNetworkConnected() : Boolean

    fun showLoading()

    fun hideLoading()

    fun openActivityOnTokenExpire()

    fun onError(@StringRes resId: Int)

    fun onError(message: String?)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun hideKeyboard()

    fun showToast(message: String, duration: Int)

    fun goToActivity(c: Class<*>, bundle: Bundle?, isFinish: Boolean)

    fun goToActivityClearAllStack(c: Class<*>, bundle: Bundle?)

    fun goToActivityForResult(c: Class<*>, bundle: Bundle?, result: Int)

    fun finishActivityforResult(bundle: Bundle?, result: Int)

    fun gotoFragment(contentReplace: Int, data: Bundle?, classFragment: Class<*>)

    fun showProgressDialog(message: String, cancelable: Boolean)

    fun dismissProgressDialog()

    fun someError(tag : String)
}