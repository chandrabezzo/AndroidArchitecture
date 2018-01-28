package com.chandraabdulfattah.coremvp.data.local.sampleDB.daoTable

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.chandraabdulfattah.coremvp.data.local.sampleDB.SampleDBConstant
import com.chandraabdulfattah.coremvp.data.model.local.UserLokal
import io.reactivex.Flowable



/**
 * Created by bezzo on 11/01/18.
 */

@Dao
interface UserDao {

    @Query("SELECT * FROM " + SampleDBConstant.USER)
    fun getAll(): Flowable<List<UserLokal>>

    @Query("SELECT * FROM " + SampleDBConstant.USER
            + " LIMIT 1")
    fun getOne(): Flowable<UserLokal>

    @Query("SELECT * FROM " + SampleDBConstant.USER
            + " WHERE id=:id")
    fun get(id: Int): Flowable<UserLokal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(informasiPengajuanLembur: UserLokal)

    @Query("DELETE FROM " + SampleDBConstant.USER)
    fun deleteAll()

    @Query("DELETE FROM " + SampleDBConstant.USER
            + " WHERE id=:id")
    fun delete(id: Int)

    @Query("SELECT COUNT(*) FROM " + SampleDBConstant.USER)
    fun count(): Int

    @Query("delete from sqlite_sequence where name='"+ SampleDBConstant.USER +"'")
    fun clearSequence()
}