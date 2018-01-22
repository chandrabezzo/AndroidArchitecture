package com.chandraabdulfattah.coremvp.ui.base

import android.content.Context

/**
 * Created by bezzo on 21/12/17.
 */

interface BaseDialogFragmentView : BaseView {

    fun getContext(): Context?

    fun dismissDialog(tag: String)
}