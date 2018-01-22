package com.chandraabdulfattah.coremvp.data.local

import android.content.Context
import com.chandraabdulfattah.coremvp.data.local.sampleDB.SampleDatabase
import com.chandraabdulfattah.coremvp.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bezzo on 11/01/18.
 */

@Singleton
class LocalStorageHelper @Inject
constructor(@ApplicationContext context: Context) : LocalStorageHelperContract {

    // add all Database Local
    val sampleDatabase : SampleDatabase = SampleDatabase.getInstance(context)
}