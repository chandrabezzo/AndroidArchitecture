package com.chandraabdulfattah.coremvp.data.local.sampleDB.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.chandraabdulfattah.coremvp.data.model.UserLokal
import com.chandraabdulfattah.coremvp.util.constanta.AppConstans
import io.reactivex.Flowable


/**
 * Created by bezzo on 11/01/18.
 * if you want to check the value is null or not use "attributeName is null"
 * Room can't check boolean, boolean type will convert to numeric
 * if your column name isAttribute, will detected automatic same as boolean value
 * if your column value is object or array you must add converter
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM " + AppConstans.USER)
    fun getAll(): Flowable<List<UserLokal>>

    @Query("SELECT * FROM " + AppConstans.USER
            + " LIMIT 1")
    fun getOne(): Flowable<UserLokal>

    @Query("SELECT * FROM " + AppConstans.USER
            + " WHERE id=:id")
    fun get(id: Int): Flowable<UserLokal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(informasiPengajuanLembur: UserLokal)

    @Query("DELETE FROM " + AppConstans.USER)
    fun deleteAll()

    @Query("DELETE FROM " + AppConstans.USER
            + " WHERE id=:id")
    fun delete(id: Int)

    @Query("SELECT COUNT(*) FROM " + AppConstans.USER)
    fun count(): Int
}