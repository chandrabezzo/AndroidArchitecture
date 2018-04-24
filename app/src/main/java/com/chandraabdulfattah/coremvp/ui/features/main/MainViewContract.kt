package com.chandraabdulfattah.coremvp.ui.features.main

import com.chandraabdulfattah.coremvp.data.model.UserLokal
import com.chandraabdulfattah.coremvp.data.model.User
import com.chandraabdulfattah.coremvp.ui.base.BaseActivityView

/**
 * Created by bezzo on 24/01/18.
 */
interface MainViewContract : BaseActivityView {

    fun showUserApi(user : User)

    fun showUserLokal(userLokal: UserLokal)
}