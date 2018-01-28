package com.chandraabdulfattah.coremvp.data.network

import com.chandraabdulfattah.coremvp.util.AppLogger
import com.chandraabdulfattah.coremvp.util.constanta.ApiConstans

import org.json.JSONException
import org.json.JSONObject

import io.reactivex.functions.Consumer

/**
 * Created by bezzo on 19/10/17.
 */

abstract class Response200<M> : Consumer<M> {

    @Throws(JSONException::class)
    abstract fun onSuccess(model: M)

    abstract fun onUnauthorized()
    @Throws(JSONException::class)
    abstract fun onError(model: M)

    @Throws(Exception::class)
    override fun accept(m: M) {
        if ((m as JSONObject).getInt(ApiConstans.STATUS_CODE) == 200) {
            onSuccess(m)
        } else if ((m as JSONObject).getInt(ApiConstans.STATUS_CODE) == 401) {
            onUnauthorized()
        } else {
            AppLogger.e(m.optString(ApiConstans.MESSAGE))
            onError(m)
        }
    }
}
