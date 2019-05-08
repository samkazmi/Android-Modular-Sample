package samkazmi.example.repositories.usecases

import samkazmi.example.datainterfaces.models.Message
import samkazmi.example.datainterfaces.repository.AuthRepository
import samkazmi.example.datainterfaces.usecases.SplashUsecase
import samkazmi.example.repositories.utils.ParseErrors
import samkazmi.example.repositories.utils.PreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashUsecaseImp @Inject constructor(
    private val authRepository: AuthRepository,
    private val preferencesHelper: PreferencesHelper,
    parseErrors: ParseErrors
) :
    BaseUsecase(parseErrors), SplashUsecase {


    override suspend fun validateSession(
        onLoading: (boolean: Boolean) -> Unit,
        onSuccess: (boolean: Boolean) -> Unit,
        onError: (message: Message) -> Unit
    ) {
        onLoading(true)
        println("onLoad:  ${Thread.currentThread()}")
        try {
            val result = withContext(Dispatchers.IO) {
                println("doing job in:  ${Thread.currentThread()}")
                authRepository.validateSession().await()
            }
            if (result.isSuccessful) {
                val success = withContext(Dispatchers.IO) {
                    println("creating success method:  ${Thread.currentThread()}")
                    // TODO Save Info
                    // preferencesHelper.saveAuthHeaders()
                    result.isSuccessful
                }
                println("onSuccess:  ${Thread.currentThread()}")
                onSuccess(success)
                onLoading(false)
            } else {
                handleError(result.code(), result.errorBody()?.string() ?: result.message(), onError)
                onLoading(false)
            }
        } catch (e: Exception) {
            handleException(e, onError)
            onLoading(false)
        }
    }

    override suspend fun logout(
        onLoading: (boolean: Boolean) -> Unit,
        onSuccess: (boolean: Boolean) -> Unit,
        onError: (message: Message) -> Unit
    ) {
        onLoading(true)
        println("onLoad:  ${Thread.currentThread()}")
        try {
            val result = withContext(Dispatchers.IO) {
                println("doing job in:  ${Thread.currentThread()}")
                authRepository.logout().await()
            }
            if (result.isSuccessful) {
                val success = withContext(Dispatchers.IO) {
                    println("creating success method:  ${Thread.currentThread()}")
                    // TODO Remove Info
                    preferencesHelper.removeHeaders()
                    preferencesHelper.removeUserInfo()
                    result.isSuccessful
                }
                println("onSuccess:  ${Thread.currentThread()}")
                onSuccess(success)
                onLoading(false)
            } else {
                handleError(result.code(), result.errorBody()?.string() ?: result.message(), onError)
                onLoading(false)
            }
        } catch (e: Exception) {
            handleException(e, onError)
            onLoading(false)
        }
    }

    override fun showOnboardingScreen() = preferencesHelper.isOnboardingShown


}