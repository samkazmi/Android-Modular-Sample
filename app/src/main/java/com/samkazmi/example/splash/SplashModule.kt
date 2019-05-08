package com.samkazmi.example.splash

import com.samkazmi.example.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import samkazmi.example.datainterfaces.repository.AuthRepository
import samkazmi.example.datainterfaces.usecases.SplashUsecase
import samkazmi.example.repositories.usecases.SplashUsecaseImp
import samkazmi.example.repositories.utils.ParseErrors
import samkazmi.example.repositories.utils.PreferencesHelper

@Module
class SplashModule {

    @Provides
    @ActivityScope
    fun provideSplashUsecase(
        authRepository: AuthRepository,
        preferencesHelper: PreferencesHelper,
        parseErrors: ParseErrors
    ): SplashUsecase =
        SplashUsecaseImp(authRepository, preferencesHelper, parseErrors)

}