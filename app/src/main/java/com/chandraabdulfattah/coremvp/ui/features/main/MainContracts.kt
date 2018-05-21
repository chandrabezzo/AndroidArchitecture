package com.chandraabdulfattah.coremvp.ui.features.main

import com.chandraabdulfattah.coremvp.data.model.User
import com.chandraabdulfattah.coremvp.data.model.UserLokal
import com.chandraabdulfattah.coremvp.ui.base.BasePresenterContract
import com.chandraabdulfattah.coremvp.ui.base.BaseView

class MainContracts {

    interface View : BaseView {
        fun showUserApi(user : User)

        fun showUserLokal(userLokal: UserLokal)
    }

    interface Presenter<V : View> : BasePresenterContract<V> {
        fun getUserApi()

        fun getUserLokal()
    }
}