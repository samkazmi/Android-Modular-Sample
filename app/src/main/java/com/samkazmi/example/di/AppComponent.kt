package com.samkazmi.example.di

import android.app.Application
import com.samkazmi.example.MyApplication
import com.samkazmi.example.di.activitylevel.ActivityBuilderModule
import com.samkazmi.example.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient

@AppScope
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, AppModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

    override fun inject(instance: MyApplication)

    fun okhttpClient(): OkHttpClient

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
