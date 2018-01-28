package com.chandraabdulfattah.coremvp.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.chandraabdulfattah.coremvp.di.PerActivity
import com.chandraabdulfattah.coremvp.ui.features.main.MainPresenter
import com.chandraabdulfattah.coremvp.ui.features.main.MainPresenterContract
import com.chandraabdulfattah.coremvp.ui.features.main.MainViewContract
import com.chandraabdulfattah.coremvp.util.rx.SchedulerProvider
import com.chandraabdulfattah.coremvp.util.rx.SchedulerProviderContract
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by bezzo on 26/09/17.
 * Use @PerActivity if view of presenter is Activity
 */

@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): AppCompatActivity {
        return mActivity
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProviderContract {
        return SchedulerProvider()
    }

    @Provides
    fun provideLinearLayoutManager(activity: AppCompatActivity): LinearLayoutManager {
        return LinearLayoutManager(activity)
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @PerActivity
    fun provideMainPresenter(presenter: MainPresenter<MainViewContract>): MainPresenterContract<MainViewContract> {
        return presenter
    }
}
