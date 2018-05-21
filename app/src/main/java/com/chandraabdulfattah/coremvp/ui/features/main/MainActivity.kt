package com.chandraabdulfattah.coremvp.ui.features.main

import android.os.Bundle
import android.widget.Toast
import com.chandraabdulfattah.coremvp.R
import com.chandraabdulfattah.coremvp.data.model.UserLokal
import com.chandraabdulfattah.coremvp.data.model.User
import com.chandraabdulfattah.coremvp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContracts.View {

    @Inject
    lateinit var presenter : MainPresenter<MainContracts.View>

    override fun onInitializedView(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        presenter.onAttach(this)

        setActionBarTitle(getString(R.string.beranda))
        displayHome()

        presenter.getUserApi()
        presenter.getUserLokal()
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showUserApi(user: User) {
        tv_api.text = user.nama + " - " + user.jabatan
    }

    override fun showUserLokal(userLokal: UserLokal) {
        tv_lokal.text = userLokal.nama + " - " + userLokal.jabatan

        showToast(userLokal.id.toString(), Toast.LENGTH_SHORT)
    }
}
