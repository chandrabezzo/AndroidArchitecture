package com.chandraabdulfattah.coremvp.data.local.sampleDB.daoTable

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.chandraabdulfattah.coremvp.data.local.sampleDB.SampleDBConstant
import com.chandraabdulfattah.coremvp.data.model.local.SampleTable
import io.reactivex.Flowable



/**
 * Created by bezzo on 11/01/18.
 */

@Dao
interface SampleDao {

    @Query("SELECT * FROM " + SampleDBConstant.SAMPLE_TABLE)
    fun getAll(): Flowable<List<SampleTable>>

    @Query("SELECT * FROM " + SampleDBConstant.SAMPLE_TABLE
            + " LIMIT 1")
    fun getOne(): Flowable<SampleTable>

    @Query("SELECT * FROM " + SampleDBConstant.SAMPLE_TABLE
            + " WHERE id=:id")
    fun get(id: Int): Flowable<SampleTable>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(informasiPengajuanLembur: SampleTable)

    @Query("DELETE FROM " + SampleDBConstant.SAMPLE_TABLE)
    fun deleteAll()

    @Query("DELETE FROM " + SampleDBConstant.SAMPLE_TABLE
            + " WHERE id=:id")
    fun delete(id: Int)

    @Query("SELECT COUNT(*) FROM " + SampleDBConstant.SAMPLE_TABLE)
    fun count(): Int

    @Query("delete from sqlite_sequence where name='"+ SampleDBConstant.SAMPLE_TABLE
            +"'")
    fun clearSequence()
}