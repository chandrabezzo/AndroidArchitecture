package com.chandraabdulfattah.coremvp.data

import android.content.Context

import com.chandraabdulfattah.coremvp.data.local.LocalStorageHelper
import com.chandraabdulfattah.coremvp.data.local.LocalStorageHelperContract
import com.chandraabdulfattah.coremvp.data.network.ApiHelperContract
import com.chandraabdulfattah.coremvp.data.session.SessionHelperContract
import com.chandraabdulfattah.coremvp.di.ApplicationContext
import com.google.gson.JsonObject
import com.rx2androidnetworking.Rx2ANRequest
import org.json.JSONObject

import java.io.File
import java.util.HashMap

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bezzo on 26/09/17.
 */

@Singleton
class DataManager @Inject
constructor(@ApplicationContext private val mContext: Context,
            private val mLocalStorageHelper: LocalStorageHelperContract,
            private val mSessionHelper: SessionHelperContract,
            private val mApiHelper: ApiHelperContract, @Inject override val localStorageHelper: LocalStorageHelper)
    : DataManagerContract {

    override operator fun get(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?,
                              headers: Map<String, String>?): Rx2ANRequest {
        return mApiHelper.get(endpoint, params, paths, headers)
    }

    override fun post(endpoint: String, headers: Map<String, String>?,
                      paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest {
        return mApiHelper.post(endpoint, headers, paths, body)
    }

    override fun post(endpoint: String, headers: Map<String, String>?,
                      paths: Map<String, String>?, body: Any): Rx2ANRequest {
        return mApiHelper.post(endpoint, headers, paths, body)
    }

    override fun postJson(endpoint: String, headers: Map<String, String>?,
                          paths: Map<String, String>?, body: JSONObject): Rx2ANRequest {
        return mApiHelper.postJson(endpoint, headers, paths, body)
    }

    override fun postJsonObject(endpoint: String, headers: Map<String, String>?,
                                paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest {
        return mApiHelper.postJsonObject(endpoint, headers, paths, jsonObject)
    }

    override fun postFile(endpoint: String, headers: Map<String, String>?,
                          paths: Map<String, String>?, file: File): Rx2ANRequest {
        return mApiHelper.postFile(endpoint, headers, paths, file)
    }

    override fun put(endpoint: String, headers: Map<String, String>?,
                     paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest {
        return mApiHelper.put(endpoint, headers, paths, body)
    }

    override fun put(endpoint: String, headers: Map<String, String>?,
                     paths: Map<String, String>?, body: Any): Rx2ANRequest {
        return mApiHelper.put(endpoint, headers, paths, body)
    }

    override fun putJson(endpoint: String, headers: Map<String, String>?,
                         paths: Map<String, String>?, body: JsonObject): Rx2ANRequest {
        return mApiHelper.putJson(endpoint, headers, paths, body)
    }

    override fun putFile(endpoint: String, headers: Map<String, String>?,
                         paths: Map<String, String>?, file: File): Rx2ANRequest {
        return mApiHelper.putFile(endpoint, headers, paths, file)
    }

    override fun putJsonObject(endpoint: String, headers: Map<String, String>?,
                               paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest {
        return mApiHelper.putJsonObject(endpoint, headers, paths, jsonObject)
    }

    override fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?): Rx2ANRequest {
        return mApiHelper.delete(endpoint, headers, paths)
    }

    override fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?, body: Map<String, String>?): Rx2ANRequest {
        return mApiHelper.delete(endpoint, headers, paths, body)
    }

    override fun delete(endpoint: String, headers: Map<String, String>?,
                        paths: Map<String, String>?, body: Any): Rx2ANRequest {
        return mApiHelper.delete(endpoint, headers, paths, body)
    }

    override fun deleteJson(endpoint: String, headers: Map<String, String>?,
                            paths: Map<String, String>?, body: JSONObject): Rx2ANRequest {
        return mApiHelper.deleteJson(endpoint, headers, paths, body)
    }

    override fun deleteJsonObject(endpoint: String, headers: Map<String, String>?,
                                  paths: Map<String, String>?, jsonObject: Any): Rx2ANRequest {
        return mApiHelper.deleteJsonObject(endpoint, headers, paths, jsonObject)
    }

    override fun download(endpoint: String, savedLocation: String, fileName: String,
                          params: Map<String, String>?, paths: Map<String, String>?,
                          headers: Map<String, String>?): Rx2ANRequest {
        return mApiHelper.download(endpoint, savedLocation, fileName, params, paths, headers)
    }

    override fun upload(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?,
                        headers: Map<String, String>?, parameterFile: String, file: File,
                        multipart: Map<String, String>?): Rx2ANRequest {
        return mApiHelper.upload(endpoint, params, paths, headers, parameterFile, file, multipart)
    }

    override fun setLogin(isLogin: Boolean) {
        mSessionHelper.setLogin(isLogin)
    }

    override fun isLogin(): Boolean? {
        return mSessionHelper.isLogin()
    }

    override fun getSession(key: String, defaultValue: String): String {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Int?): Int? {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Double?): Double? {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Boolean?): Boolean? {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Long?): Long? {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Short?): Short? {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Byte?): Byte? {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Char?): Char? {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Float?): Float? {
        return mSessionHelper.getSession(key, defaultValue)
    }

    override fun addSession(key: String, value: String) {
        mSessionHelper.addSession(key, value)
    }

    override fun addSession(key: String, value: Int?) {
        mSessionHelper.addSession(key, value)
    }

    override fun addSession(key: String, value: Double?) {
        mSessionHelper.addSession(key, value)
    }

    override fun addSession(key: String, value: Boolean?) {
        mSessionHelper.addSession(key, value)
    }

    override fun addSession(key: String, value: Long?) {
        mSessionHelper.addSession(key, value)
    }

    override fun addSession(key: String, value: Short?) {
        mSessionHelper.addSession(key, value)
    }

    override fun addSession(key: String, value: Byte?) {
        mSessionHelper.addSession(key, value)
    }

    override fun addSession(key: String, value: Char?) {
        mSessionHelper.addSession(key, value)
    }

    override fun addSession(key: String, value: Float?) {
        mSessionHelper.addSession(key, value)
    }

    override fun deleteSession(key: String) {
        mSessionHelper.deleteSession(key)
    }

    override fun clearSession() {
        mSessionHelper.clearSession()
    }

    override fun uploads(endpoint: String, params: Map<String, String>,
                         paths: Map<String, String>, headers: Map<String, String>,
                         files: Map<String, File>, multipart: Map<String, String>): Rx2ANRequest {
        return mApiHelper.uploads(endpoint, params, paths, headers, files, multipart)
    }

    companion object {

        private val TAG = "DataManager"
    }
}
