package com.chandraabdulfattah.coremvp.data.session

/**
 * Created by bezzo on 11/01/18.
 */
interface SessionHelperContract {
    fun setLogin(isLogin: Boolean)

    fun isLogin(): Boolean?

    fun getSession(key: String, defaultValue: String): String

    fun getSession(key: String, defaultValue: Int?): Int?

    fun getSession(key: String, defaultValue: Double?): Double?

    fun getSession(key: String, defaultValue: Boolean?): Boolean?

    fun getSession(key: String, defaultValue: Long?): Long?

    fun getSession(key: String, defaultValue: Short?): Short?

    fun getSession(key: String, defaultValue: Byte?): Byte?

    fun getSession(key: String, defaultValue: Char?): Char?

    fun getSession(key: String, defaultValue: Float?): Float?

    fun addSession(key: String, value: String)

    fun addSession(key: String, value: Int?)

    fun addSession(key: String, value: Double?)

    fun addSession(key: String, value: Boolean?)

    fun addSession(key: String, value: Long?)

    fun addSession(key: String, value: Short?)

    fun addSession(key: String, value: Byte?)

    fun addSession(key: String, value: Char?)

    fun addSession(key: String, value: Float?)

    fun deleteSession(key: String)

    fun clearSession()
}