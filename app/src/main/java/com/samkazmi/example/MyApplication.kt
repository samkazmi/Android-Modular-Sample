package com.samkazmi.example

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.multidex.MultiDex
import com.samkazmi.example.di.AppComponent
import com.samkazmi.example.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import samkazmi.example.base.Navigator
import samkazmi.example.home.ui.HomeActivity
import samkazmi.example.onboarding.ui.OnboardingActivity
import samkazmi.example.splash.ui.SplashActivity

class MyApplication : DaggerApplication(), Navigator {

    companion object {
        private var application: MyApplication? = null
        fun getInstance(): MyApplication? {
            return application
        }
    }

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        //   Fabric.with(this, Crashlytics())
        application = this
        //  setHeaders()
    }


     /*fun setHeaders() {
         for (interceptor in appComponent!!.okhttpClient().interceptors()) {
             if (interceptor is SessionAuth) {
                 (interceptor as SessionAuth).setAuthHeader(userRepository!!.getAuthHeaders())
             }
         }
     }*/

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun getAppComponent(): AppComponent? {
        return appComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent?.inject(this)
        return appComponent as AppComponent
    }

    //  private val isLoggedInCallback = Observer<Boolean> { setHeaders() }
    override fun startModule(activity: Activity, modules: Navigator.Modules, bundle: Bundle?, startForResult: Int?) {
        val intent = Intent()
        when (modules) {
            Navigator.Modules.SPLASH -> intent.setClass(activity, SplashActivity::class.java)
            Navigator.Modules.ONBOARDING -> intent.setClass(activity, OnboardingActivity::class.java)
            Navigator.Modules.HOME -> intent.setClass(activity, HomeActivity::class.java)
            Navigator.Modules.AUTH ->{}
            Navigator.Modules.CHARGING -> {}
            Navigator.Modules.PAYMENT -> {}
            Navigator.Modules.ORDERS -> {}
        }
        if (bundle != null) {
            intent.putExtra(Navigator.EXTRAS, bundle)
        }
        if (startForResult == null) {
            activity.startActivity(intent)
        } else {
            activity.startActivityForResult(intent, startForResult)
        }
    }


}