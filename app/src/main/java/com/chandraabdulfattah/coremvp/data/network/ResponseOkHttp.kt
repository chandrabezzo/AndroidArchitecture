package com.chandraabdulfattah.coremvp.data.network

import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener
import com.chandraabdulfattah.coremvp.util.constanta.ApiConstans
import io.reactivex.functions.Consumer
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by bezzo on 19/10/17.
 */

abstract class ResponseOkHttp constructor(val successCode : Int)
    : OkHttpResponseAndJSONObjectRequestListener {

    override fun onResponse(response: Response, value : JSONObject) {
        when {
            response.code() == successCode -> onSuccess(value)
            response.code() == 401 -> onUnauthorized()
            else -> onError(value)
        }
    }

    @Throws(JSONException::class)
    abstract fun onSuccess(model: JSONObject)

    abstract fun onUnauthorized()

    @Throws(JSONException::class)
    abstract fun onError(model: JSONObject)
}
