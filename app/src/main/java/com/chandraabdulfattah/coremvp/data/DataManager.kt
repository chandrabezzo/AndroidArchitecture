package com.chandraabdulfattah.coremvp.data

import android.content.Context
import com.chandraabdulfattah.coremvp.data.local.LocalStorageHelper
import com.chandraabdulfattah.coremvp.data.local.LocalStorageHelperContract
import com.chandraabdulfattah.coremvp.data.network.ApiHelperContract
import com.chandraabdulfattah.coremvp.data.session.SessionHelperContract
import com.chandraabdulfattah.coremvp.di.ApplicationContext
import com.rx2androidnetworking.Rx2ANRequest
import org.json.JSONObject
import java.io.File
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
            private val mApiHelper: ApiHelperContract)
    : DataManagerContract {

    @Inject
    lateinit var local : LocalStorageHelper

    override val localStorageHelper: LocalStorageHelper
        get() = local

    override operator fun get(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?,
                              headers: Map<String, String>?): Rx2ANRequest {
        return mApiHelper.get(endpoint, params, paths, headers)
    }

    override fun post(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?, headers: Map<String, String>?, body: Any?): Rx2ANRequest {
        return mApiHelper.post(endpoint, params, paths, headers, body)
    }

    override fun put(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?, headers: Map<String, String>?, body: Any?): Rx2ANRequest {
        return mApiHelper.put(endpoint, params, paths, headers, body)
    }

    override fun delete(endpoint: String, params: Map<String, String>?, paths: Map<String, String>?, headers: Map<String, String>?, body: Any?): Rx2ANRequest {
        return mApiHelper.delete(endpoint, params, paths, headers, body)
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
