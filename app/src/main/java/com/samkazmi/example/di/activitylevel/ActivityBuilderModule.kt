package com.samkazmi.example.di.activitylevel


import com.samkazmi.example.di.scope.ActivityScope
import com.samkazmi.example.home.HomeBuilderModule
import com.samkazmi.example.home.HomeModule
import com.samkazmi.example.onboarding.OnboardingBuilderModule
import com.samkazmi.example.onboarding.OnboardingModule
import com.samkazmi.example.splash.SplashBuilderModule
import com.samkazmi.example.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import samkazmi.example.home.ui.HomeActivity
import samkazmi.example.onboarding.ui.OnboardingActivity
import samkazmi.example.splash.ui.SplashActivity


@Suppress("unused")
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashBuilderModule::class, SplashModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [OnboardingBuilderModule::class, OnboardingModule::class])
    abstract fun contributeOnboardingActivity(): OnboardingActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeBuilderModule::class, HomeModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}