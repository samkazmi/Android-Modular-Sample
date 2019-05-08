package com.samkazmi.example.di.activitylevel

import dagger.Module
import dagger.Provides
import samkazmi.example.datainterfaces.repository.AuthRepository
import samkazmi.example.repositories.AuthRepositoryImp
import samkazmi.example.repositories.remote.api.AuthApi
import retrofit2.Retrofit

@Module(includes = [ViewModelModule::class])
class RepositoryModule {

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    fun provideAuthRepository(authApi: AuthApi): AuthRepository = AuthRepositoryImp(authApi)

}
// Here we provide a common repository that has @AppScope and used by multiple modules(by modules I mean Activity)
