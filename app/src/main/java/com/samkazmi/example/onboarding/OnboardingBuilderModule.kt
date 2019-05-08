package com.samkazmi.example.onboarding

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import samkazmi.example.base.vm.ViewModelKey
import samkazmi.example.onboarding.ui.OnboardingFragment
import samkazmi.example.onboarding.viewmodel.OnboardingViewModel

@Suppress("unused")
@Module
abstract class OnboardingBuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    abstract fun bindOnBoardingViewModel(viewModel: OnboardingViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeOnboardingFragment(): OnboardingFragment

}
