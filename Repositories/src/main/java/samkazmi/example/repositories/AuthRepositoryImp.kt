package samkazmi.example.repositories

import samkazmi.example.datainterfaces.repository.AuthRepository
import samkazmi.example.repositories.remote.api.AuthApi
import kotlinx.coroutines.Deferred
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override fun validateSession(): Deferred<Response<*>> = authApi.validateSession()
    override fun loginWithPhone(): Deferred<Response<*>> = authApi.loginWithPhone()
    override fun loginWithEmail(): Deferred<Response<*>> = authApi.loginWithEmail()
    override fun signUpWithPhone(): Deferred<Response<*>> = authApi.signUpWithPhone()
    override fun signUpWithEmail(): Deferred<Response<*>> = authApi.signUpWithEmail()
    override fun signUpWithFacebook(): Deferred<Response<*>> = authApi.signUpWithFacebook()
    override fun verifyPinForSignUp(): Deferred<Response<*>> = authApi.verifyPinForSignUp()
    override fun forgotPassword(): Deferred<Response<*>> = authApi.forgotPassword()
    override fun resetPassword(): Deferred<Response<*>> = authApi.resetPassword()
    override fun logout(): Deferred<Response<*>> = authApi.logout()
}