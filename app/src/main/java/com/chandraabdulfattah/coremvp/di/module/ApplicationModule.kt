package com.chandraabdulfattah.coremvp.di.module

import android.app.Application
import android.content.Context
import com.chandraabdulfattah.coremvp.data.DataManager
import com.chandraabdulfattah.coremvp.data.DataManagerContract
import com.chandraabdulfattah.coremvp.data.local.LocalStorageHelper
import com.chandraabdulfattah.coremvp.data.local.LocalStorageHelperContract
import com.chandraabdulfattah.coremvp.data.network.ApiHelper
import com.chandraabdulfattah.coremvp.data.network.ApiHelperContract
import com.chandraabdulfattah.coremvp.data.session.SessionHelper
import com.chandraabdulfattah.coremvp.data.session.SessionHelperContract
import com.chandraabdulfattah.coremvp.di.ApplicationContext
import com.chandraabdulfattah.coremvp.util.constanta.AppConstans
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by bezzo on 26/09/17.
 */

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    fun provideDatabaseName(): String {
        return AppConstans.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: DataManager): DataManagerContract {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideLocalStorageHelper(appLocalStorage: LocalStorageHelper): LocalStorageHelperContract {
        return appLocalStorage
    }

    @Provides
    @Singleton
    fun provideSessionHelper(sessionHelper: SessionHelper): SessionHelperContract {
        return sessionHelper
    }

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: ApiHelper): ApiHelperContract {
        return appApiHelper
    }
}
