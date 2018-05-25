package com.chandraabdulfattah.coremvp.ui.features.main

import com.androidnetworking.error.ANError
import com.chandraabdulfattah.coremvp.data.DataManagerContract
import com.chandraabdulfattah.coremvp.data.model.Jabatan
import com.chandraabdulfattah.coremvp.data.model.User
import com.chandraabdulfattah.coremvp.data.network.ApiEndPoint
import com.chandraabdulfattah.coremvp.data.network.ResponseHandler
import com.chandraabdulfattah.coremvp.ui.base.BasePresenter
import com.chandraabdulfattah.coremvp.util.constanta.ApiConstans
import com.chandraabdulfattah.coremvp.util.rx.SchedulerProviderContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.realm.Realm
import io.realm.Sort
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by bezzo on 24/01/18.
 * if you use kotlin, when send to view you must add "?" for null check pointer
 * but if you use java, when send to view you must add if(!isViewAttached) return;
 * before you send data to view
 */

class MainPresenter<V : MainContracts.View> @Inject
constructor(dataManager: DataManagerContract, schedulerProvider: SchedulerProviderContract, compositeDisposable: CompositeDisposable)
    : BasePresenter<V>(dataManager, schedulerProvider, compositeDisposable), MainContracts.Presenter<V> {

    var realm = Realm.getDefaultInstance()

    override fun getUserLokal() {
        var userLocal = dataManager.localGet(User::class.java, User.ID, 1.toInt())
        view?.showUser(userLocal)
    }

    override fun getUserApi() {
        compositeDisposable.add(dataManager.get(ApiEndPoint.USER, null, null, null)
                .jsonObjectObservable
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(object : ResponseHandler<JSONObject>(200) {
                    override fun onSuccess(model: JSONObject) {
                        dataManager.localClear(User::class.java)
                        dataManager.localCreateFromJSON(User::class.java, model.optJSONObject(ApiConstans.DATA))
                        getUserLokal()
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
                }))
    }

    override fun getJabatanApi() {
        compositeDisposable.add(dataManager.get(ApiEndPoint.JABATAN, null, null, null)
                .jsonObjectObservable
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(object : ResponseHandler<JSONObject>(200){
                    override fun onSuccess(model: JSONObject) {
                        dataManager.localClear(Jabatan::class.java)
                        dataManager.localCreateFromJSON(Jabatan::class.java, model.optJSONObject(ApiConstans.DATA))
                        getJabatanLocal()
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
                }))
    }

    override fun getJabatanLocal() {
        var values = dataManager.localGetAll(Jabatan::class.java, "id", Sort.ASCENDING)
        view?.showJabatan(values)
    }
}