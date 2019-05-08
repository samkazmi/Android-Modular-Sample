package com.samkazmi.example.home

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import samkazmi.example.base.vm.ViewModelKey
import samkazmi.example.home.ui.HomeFragment
import samkazmi.example.home.vm.HomeViewModel

@Suppress("unused")
@Module
abstract class HomeBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}
