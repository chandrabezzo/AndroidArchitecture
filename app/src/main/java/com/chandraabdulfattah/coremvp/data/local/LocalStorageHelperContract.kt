package com.chandraabdulfattah.coremvp.data.local

import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.Sort
import org.json.JSONObject
import java.util.*

interface LocalStorageHelperContract {

    fun localRefresh()

    fun <T : RealmModel> localClear(c : Class<T>)

    fun <T : RealmModel> localGetAll(c : Class<T>): RealmResults<T>

    fun <T : RealmModel> localGetAll(c : Class<T>, fieldSort : String, sortType : Sort): RealmResults<T>

    fun <T : RealmModel> localCreateFromJSON(c : Class<T>, value : JSONObject)

    fun localClose()

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Int?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : String?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Byte?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : ByteArray?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Short?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Long?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Double?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Float?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Boolean?) : T?

    fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Date?) : T?
}
