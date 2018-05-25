package com.chandraabdulfattah.coremvp.data.local

import android.content.Context
import com.chandraabdulfattah.coremvp.di.ApplicationContext
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults
import io.realm.Sort
import org.json.JSONObject
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorageHelper @Inject
constructor(@ApplicationContext context: Context) : LocalStorageHelperContract {

    var realm = Realm.getDefaultInstance()

    override fun <T : RealmModel> localGetAll(c : Class<T>): RealmResults<T> {
        return realm.where(c).findAll()
    }

    override fun <T : RealmModel> localGetAll(c : Class<T>, fieldSort : String, sortType : Sort): RealmResults<T>{
        return realm.where(c).sort(fieldSort, sortType).findAll()
    }

    override fun <T : RealmModel> localCreateFromJSON(c : Class<T>, value : JSONObject){
        realm.beginTransaction()
        realm.createObjectFromJson(c, value)
        realm.commitTransaction()
    }

    override fun <T : RealmModel> localClear(c : Class<T>){
        realm.beginTransaction()
        realm.delete(c)
        realm.commitTransaction()
    }

    override fun localRefresh() {
        realm.refresh()
    }

    override fun localClose() {
        if (!realm.isClosed){
            realm.close()
        }
    }

    override fun <T : RealmModel> localGet(c : Class<T>, fieldName : String, key : Int?) : T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: String?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: Byte?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: ByteArray?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: Short?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: Long?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: Double?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: Float?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: Boolean?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }

    override fun <T : RealmModel> localGet(c: Class<T>, fieldName: String, key: Date?): T? {
        return realm.where(c).equalTo(fieldName, key).findFirst()
    }
}