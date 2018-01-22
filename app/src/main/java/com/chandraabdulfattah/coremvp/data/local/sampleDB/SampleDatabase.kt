package com.chandraabdulfattah.coremvp.data.local.sampleDB

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.chandraabdulfattah.coremvp.data.local.sampleDB.daoTable.SampleDao
import com.chandraabdulfattah.coremvp.data.model.local.SampleTable
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import android.arch.persistence.room.Room
import android.content.Context
import com.chandraabdulfattah.coremvp.di.ApplicationContext
import com.chandraabdulfattah.coremvp.util.constanta.AppConstans

/**
 * Created by bezzo on 11/01/18.
 * Add more entities = arrayOf(SampleTable::class, SampleBTable::class)
 */
@Database(entities = arrayOf(SampleTable::class), version = 1)
abstract class SampleDatabase : RoomDatabase() {

    abstract fun sampleDao() : SampleDao

    companion object {
        @Volatile private var INSTANCE: SampleDatabase? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Since we didn't alter the table, there's nothing else to do here.
            }
        }

        fun getInstance(@ApplicationContext context: Context): SampleDatabase {
            if (INSTANCE == null) {
                synchronized(SampleDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context,
                                SampleDatabase::class.java, AppConstans.DB_NAME).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}