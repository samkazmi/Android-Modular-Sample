package samkazmi.example.datainterfaces.usecases

interface OnboardingUsecase {
    suspend fun setOnBoardingShown(
        isOnBoardingShown: Boolean
    )
}