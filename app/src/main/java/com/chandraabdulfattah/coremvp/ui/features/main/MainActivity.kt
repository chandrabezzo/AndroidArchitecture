package com.chandraabdulfattah.coremvp.ui.features.main

import android.os.Bundle
import com.chandraabdulfattah.coremvp.R
import com.chandraabdulfattah.coremvp.adapter.recyclerView.JabatanRVAdapter
import com.chandraabdulfattah.coremvp.data.model.Jabatan
import com.chandraabdulfattah.coremvp.data.model.User
import com.chandraabdulfattah.coremvp.ui.base.BaseActivity
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContracts.View {

    @Inject
    lateinit var presenter : MainPresenter<MainContracts.View>

    lateinit var jabatanRVAdapter: JabatanRVAdapter

    override fun onInitializedView(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        presenter.onAttach(this)

        setActionBarTitle(getString(R.string.beranda))
        displayHome()

        presenter.getUserLokal()
        presenter.getUserApi()

        presenter.getJabatanLocal()
        presenter.getJabatanApi()
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showUser(user: User?) {
        if (user != null){
            tv_data.text = user.nama + " - " + user.jabatan
        }
    }

    override fun showJabatan(values: RealmResults<Jabatan>) {

    }
}
