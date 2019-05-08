package com.samkazmi.example.onboarding

import com.samkazmi.example.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import samkazmi.example.datainterfaces.usecases.OnboardingUsecase
import samkazmi.example.datainterfaces.usecases.SplashUsecase
import samkazmi.example.repositories.usecases.OnboardingUsecaseImp
import samkazmi.example.repositories.usecases.SplashUsecaseImp
import samkazmi.example.repositories.utils.ParseErrors
import samkazmi.example.repositories.utils.PreferencesHelper

@Module
class OnboardingModule {

    @Provides
    @ActivityScope
    fun provideOnBoardingUsecase(
        preferencesHelper: PreferencesHelper,
        parseErrors: ParseErrors
    ): OnboardingUsecase =
        OnboardingUsecaseImp(preferencesHelper, parseErrors)

}