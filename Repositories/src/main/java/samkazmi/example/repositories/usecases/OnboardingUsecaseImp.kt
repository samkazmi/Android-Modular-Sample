package samkazmi.example.repositories.usecases

import samkazmi.example.datainterfaces.usecases.OnboardingUsecase
import samkazmi.example.repositories.utils.ParseErrors
import samkazmi.example.repositories.utils.PreferencesHelper
import javax.inject.Inject

class OnboardingUsecaseImp @Inject constructor(
    private val preferencesHelper: PreferencesHelper,
    parseErrors: ParseErrors
) :
    BaseUsecase(parseErrors), OnboardingUsecase {
    override suspend fun setOnBoardingShown(isOnBoardingShown: Boolean) {
        preferencesHelper.isOnboardingShown = isOnBoardingShown
    }


}