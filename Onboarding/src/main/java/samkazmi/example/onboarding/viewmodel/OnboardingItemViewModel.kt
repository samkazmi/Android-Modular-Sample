package samkazmi.example.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import samkazmi.example.onboarding.data.Onboarding

import javax.inject.Inject

class OnboardingItemViewModel @Inject
constructor() : ViewModel() {

    var onboarding: Onboarding? = null
}
