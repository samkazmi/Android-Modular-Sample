package samkazmi.example.base

import android.app.Activity
import android.os.Bundle

interface Navigator {
    fun startModule(activity: Activity, modules: Modules, bundle: Bundle? = null, startForResult: Int? = null)
    enum class Modules {
        SPLASH, ONBOARDING, HOME, AUTH, ORDERS, PAYMENT, CHARGING,SETTINGS
    }

    companion object {
        const val EXTRAS= "extra_bundle"
        const val ONBOARDING_ARG_SHOWNFROM = "SHOWN_FROM"
    }
}