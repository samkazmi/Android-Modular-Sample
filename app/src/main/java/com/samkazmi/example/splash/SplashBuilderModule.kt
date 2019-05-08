package com.samkazmi.example.splash

import androidx.lifecycle.ViewModel

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import samkazmi.example.base.vm.ViewModelKey
import samkazmi.example.splash.ui.SplashFragment
import samkazmi.example.splash.vm.SplashViewModel

@Suppress("unused")
@Module
abstract class SplashBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

}
