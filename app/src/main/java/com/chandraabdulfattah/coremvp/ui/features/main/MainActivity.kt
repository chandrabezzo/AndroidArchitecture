package com.chandraabdulfattah.coremvp.ui.features.main

import android.os.Bundle
import android.widget.Toast
import com.chandraabdulfattah.coremvp.R
import com.chandraabdulfattah.coremvp.data.model.local.UserLokal
import com.chandraabdulfattah.coremvp.data.model.network.User
import com.chandraabdulfattah.coremvp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainViewContract {

    @Inject
    lateinit var presenter : MainPresenter<MainViewContract>

    override fun onInitializedView(savedInstanceState: Bundle?) {
        super.onInitializedView(savedInstanceState)

        activityComponent?.inject(this)
        presenter.onAttach(this)

        setActionBarTitle(getString(R.string.beranda))
        displayHome()

        presenter.getUserApi()
        presenter.getUserLokal()
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun showUserApi(user: User) {
        tv_api.text = user.nama + " - " + user.jabatan
    }

    override fun showUserLokal(userLokal: UserLokal) {
        tv_lokal.text = userLokal.nama + " - " + userLokal.jabatan

        showToast(userLokal.id.toString(), Toast.LENGTH_SHORT)
    }
}
