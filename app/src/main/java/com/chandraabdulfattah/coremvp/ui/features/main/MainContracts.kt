package com.chandraabdulfattah.coremvp.ui.features.main

import com.chandraabdulfattah.coremvp.data.model.Jabatan
import com.chandraabdulfattah.coremvp.data.model.User
import com.chandraabdulfattah.coremvp.ui.base.BasePresenterContract
import com.chandraabdulfattah.coremvp.ui.base.BaseView
import io.realm.RealmResults

class MainContracts {

    interface View : BaseView {
        fun showUser(user : User?)

        fun showJabatan(values : RealmResults<Jabatan>)
    }

    interface Presenter<V : View> : BasePresenterContract<V> {
        fun getUserApi()

        fun getUserLokal()

        fun getJabatanApi()

        fun getJabatanLocal()
    }
}