package samkazmi.example.datainterfaces.repository

import kotlinx.coroutines.Deferred
import retrofit2.Response

interface AuthRepository {
    fun validateSession(): Deferred<Response<*>>
    fun loginWithPhone(): Deferred<Response<*>>
    fun loginWithEmail(): Deferred<Response<*>>
    fun signUpWithPhone(): Deferred<Response<*>>
    fun signUpWithEmail(): Deferred<Response<*>>
    fun signUpWithFacebook(): Deferred<Response<*>>
    fun verifyPinForSignUp(): Deferred<Response<*>>
    fun forgotPassword(): Deferred<Response<*>>
    fun resetPassword(): Deferred<Response<*>>
    fun logout(): Deferred<Response<*>>
}