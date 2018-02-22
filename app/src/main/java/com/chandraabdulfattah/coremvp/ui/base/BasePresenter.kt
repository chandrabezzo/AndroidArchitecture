package com.chandraabdulfattah.coremvp.ui.base

import android.widget.Toast
import com.androidnetworking.error.ANError
import com.chandraabdulfattah.coremvp.data.DataManagerContract
import com.chandraabdulfattah.coremvp.data.model.network.ApiError
import com.chandraabdulfattah.coremvp.data.session.SessionConstants
import com.chandraabdulfattah.coremvp.util.AppLogger
import com.chandraabdulfattah.coremvp.util.CommonUtils
import com.chandraabdulfattah.coremvp.util.rx.SchedulerProviderContract
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by bezzo on 26/09/17.
 */

open class BasePresenter<V : BaseView> @Inject
constructor(val dataManager: DataManagerContract,
            val schedulerProvider: SchedulerProviderContract,
            val compositeDisposable: CompositeDisposable) : BasePresenterContract<V> {

    @Inject
    lateinit var gson: Gson

    var view: V? = null
        private set

    val isViewAttached: Boolean
        get() = view != null

    override fun onAttach(mvpView: V) {
        view = mvpView
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
    }

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    override fun handleApiError(error: ANError) {
        if (CommonUtils.isJSONValid(error.errorBody)){
            val apiError = gson.fromJson(error.errorBody,
                    ApiError::class.java)

            if (apiError != null) {
                if (error.errorCode == 401) {
                    logout()
                } else {
                    if (apiError.errors != null) {
                        for (counter in 0 until apiError.errors!!.size) {
                            view!!.showToast(apiError.errors!![counter].error!!,
                                    Toast.LENGTH_SHORT)
                            AppLogger.e(apiError.errors!!.get(counter).error)
                        }
                    } else {
                        view!!.showToast(apiError.message!!, Toast.LENGTH_SHORT)
                    }
                }
            } else {
                view!!.showToast(error.message!!, Toast.LENGTH_SHORT)
            }
        }
        else {
            view?.showToast("Error Response Is Not JSON Type", Toast.LENGTH_SHORT)
        }
    }

    override fun setUserAsLoggedOut() {
        dataManager
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.onAttach(BaseView) before" + " requesting data to the Presenter")

    override fun clearLog() {
        dataManager.setLogin(false)
        dataManager.deleteSession(SessionConstants.TOKEN)

        val exec = Executors.newSingleThreadExecutor()
        exec.execute { }
    }

    override fun logout() {
        clearLog()

        view?.showToast("You Have No Access", Toast.LENGTH_SHORT)
        view!!.openActivityOnTokenExpire()
    }

    companion object {
        private val TAG = "BasePresenter"
    }

    override fun logging(message: String) {
        AppLogger.i(message)
        view?.showToast(message, Toast.LENGTH_SHORT)
    }
}
