package samkazmi.example.repositories.remote.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface AuthApi {

    @GET("validateSession")
    fun validateSession(): Deferred<Response<*>>

    @GET("auth/login_phone")
    fun loginWithPhone(): Deferred<Response<*>>

    @GET("auth/login_email")
    fun loginWithEmail(): Deferred<Response<*>>

    @GET("auth/signup_phone")
    fun signUpWithPhone(): Deferred<Response<*>>

    @GET("auth/signup_email")
    fun signUpWithEmail(): Deferred<Response<*>>

    @GET("auth/signup_facebook")
    fun signUpWithFacebook(): Deferred<Response<*>>

    @GET("auth/verify_pin")
    fun verifyPinForSignUp(): Deferred<Response<*>>

    @GET("auth/forgot_password")
    fun forgotPassword(): Deferred<Response<*>>

    @GET("auth/reset_password")
    fun resetPassword(): Deferred<Response<*>>

    @GET("auth/reset_password")
    fun logout(): Deferred<Response<*>>
}