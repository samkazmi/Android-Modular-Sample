package samkazmi.example.datainterfaces.usecases

import samkazmi.example.datainterfaces.models.Message

interface SplashUsecase {

    suspend fun validateSession(
        onLoading: (boolean: Boolean) -> Unit = {},
        onSuccess: (boolean: Boolean) -> Unit = {},
        onError: (message: Message) -> Unit = {}
    )

    suspend fun logout(
        onLoading: (boolean: Boolean) -> Unit = {},
        onSuccess: (boolean: Boolean) -> Unit = {},
        onError: (message: Message) -> Unit = {}
    )

    fun showOnboardingScreen(): Boolean
}