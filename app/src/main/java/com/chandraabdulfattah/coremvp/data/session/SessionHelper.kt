package com.chandraabdulfattah.coremvp.data.session

import com.orhanobut.hawk.Hawk

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bezzo on 11/01/18.
 */

@Singleton
class SessionHelper @Inject
constructor() : SessionHelperContract {

    override fun setLogin(isLogin: Boolean) {
        Hawk.put(SessionConstants.IS_LOGIN, isLogin)
    }

    override fun isLogin(): Boolean? {
        return Hawk.get(SessionConstants.IS_LOGIN, false)
    }

    override fun getSession(key: String, defaultValue: String): String {
        return Hawk.get(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Int?): Int? {
        return Hawk.get<Int>(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Double?): Double? {
        return Hawk.get<Double>(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Boolean?): Boolean? {
        return null
    }

    override fun getSession(key: String, defaultValue: Long?): Long? {
        return Hawk.get<Long>(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Short?): Short? {
        return Hawk.get<Short>(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Byte?): Byte? {
        return Hawk.get<Byte>(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Char?): Char? {
        return Hawk.get<Char>(key, defaultValue)
    }

    override fun getSession(key: String, defaultValue: Float?): Float? {
        return Hawk.get<Float>(key, defaultValue)
    }

    override fun addSession(key: String, value: String) {
        Hawk.put(key, value)
    }

    override fun addSession(key: String, value: Int?) {
        Hawk.put<Int>(key, value)
    }

    override fun addSession(key: String, value: Double?) {
        Hawk.put<Double>(key, value)
    }

    override fun addSession(key: String, value: Boolean?) {
        Hawk.put<Boolean>(key, value)
    }

    override fun addSession(key: String, value: Long?) {
        Hawk.put<Long>(key, value)
    }

    override fun addSession(key: String, value: Short?) {
        Hawk.put<Short>(key, value)
    }

    override fun addSession(key: String, value: Byte?) {
        Hawk.put<Byte>(key, value)
    }

    override fun addSession(key: String, value: Char?) {
        Hawk.put<Char>(key, value)
    }

    override fun addSession(key: String, value: Float?) {
        Hawk.put<Float>(key, value)
    }

    override fun deleteSession(key: String) {
        Hawk.delete(key)
    }

    override fun clearSession() {
        Hawk.deleteAll()
    }
}
