package com.chandraabdulfattah.coremvp.data.network

import com.chandraabdulfattah.coremvp.util.constanta.ApiConstans
import io.reactivex.functions.Consumer
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by bezzo on 19/10/17.
 */

abstract class ResponseHandler<M> constructor(val successCode : Int) : Consumer<M> {

    @Throws(JSONException::class)
    abstract fun onSuccess(model: M)

    abstract fun onUnauthorized()

    @Throws(JSONException::class)
    abstract fun onError(model: M)

    @Throws(Exception::class)
    override fun accept(m: M) {
        when {
            (m as JSONObject).getInt(ApiConstans.STATUS_CODE) == successCode -> onSuccess(m)
            (m as JSONObject).getInt(ApiConstans.STATUS_CODE) == 401 -> onUnauthorized()
            else -> onError(m)
        }
    }
}
