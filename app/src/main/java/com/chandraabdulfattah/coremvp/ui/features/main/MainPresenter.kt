package com.chandraabdulfattah.coremvp.ui.features.main

import com.androidnetworking.error.ANError
import com.chandraabdulfattah.coremvp.R
import com.chandraabdulfattah.coremvp.data.DataManagerContract
import com.chandraabdulfattah.coremvp.data.model.local.UserLokal
import com.chandraabdulfattah.coremvp.data.model.network.User
import com.chandraabdulfattah.coremvp.data.network.ApiEndPoint
import com.chandraabdulfattah.coremvp.data.network.Response200
import com.chandraabdulfattah.coremvp.ui.base.BasePresenter
import com.chandraabdulfattah.coremvp.util.constanta.ApiConstans
import com.chandraabdulfattah.coremvp.util.rx.SchedulerProviderContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import org.json.JSONObject
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by bezzo on 24/01/18.
 */

class MainPresenter<V : MainViewContract> @Inject
constructor(dataManager: DataManagerContract, schedulerProvider: SchedulerProviderContract, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), MainPresenterContract<V> {

    override fun getUserLokal() {
        var user = UserLokal()
        user.nama = "Bezzo"
        user.jabatan = "Android"

        val exec = Executors.newSingleThreadExecutor()
        exec.execute {
            dataManager.localStorageHelper.sampleDatabase.userDao().deleteAll()
            dataManager.localStorageHelper.sampleDatabase.userDao().clearSequence()
            dataManager.localStorageHelper.sampleDatabase.userDao()
                    .insert(user)

        }

        compositeDisposable.add(dataManager.localStorageHelper.sampleDatabase.userDao().getOne()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    view?.showUserLokal(it)
                }, {
                    if (it is ANError){
                        handleApiError(it)
                    }
                    else {
                        logging(view?.getContext()!!.getString(R.string.some_error))
                    }
                }))
    }

    override fun getUserApi() {
        compositeDisposable.add(dataManager.get(ApiEndPoint.USER, null, null, null)
                .jsonObjectObservable
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(object : Response200<JSONObject>() {
                    override fun onSuccess(model: JSONObject) {
                        var user = gson.fromJson<User>(model.optString(ApiConstans.DATA), User::class.java)

                        view?.showUserApi(user)
                    }

                    override fun onUnauthorized() {
                        logout()
                    }

                    override fun onError(model: JSONObject) {
                        logging(model.optString(ApiConstans.MESSAGE))
                    }
                }, Consumer {
                    if (it is ANError){
                        handleApiError(it)
                    }
                    else {
                        logging(view?.getContext()!!.getString(R.string.some_error))
                    }
                }))
    }


}