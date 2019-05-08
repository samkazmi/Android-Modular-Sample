package samkazmi.example.repositories.utils

import android.content.SharedPreferences
import samkazmi.example.datainterfaces.models.AuthHeader

import javax.inject.Inject

class PreferencesHelper @Inject
constructor(private val prefs: SharedPreferences) {

    private val KEY_isonboardingshown = "isOnboardingShown"
    private val KEY_sid = "sid"
    private val KEY_stoken = "stoken"

    val authHeaders: AuthHeader
        get() {
            val tempSid = prefs.getInt(KEY_sid, -1)
            val sid = if (tempSid == -1) null else tempSid
            val stoken = prefs.getString(KEY_stoken, null)
            return AuthHeader(sid, stoken)
        }

    var isOnboardingShown: Boolean
        get() = prefs.getBoolean(KEY_isonboardingshown, false)
        set(isFirstTime) = prefs.edit().putBoolean(KEY_isonboardingshown, isFirstTime).apply()

    fun saveAuthHeaders(sid: Int, stoken: String) {
        prefs.edit().putInt(KEY_sid, sid).apply()
        prefs.edit().putString(KEY_stoken, stoken).apply()
    }

    fun removeHeaders() {
        prefs.edit().remove(KEY_sid).apply()
        prefs.edit().remove(KEY_stoken).apply()
    }

    fun removeUserInfo() {
        TODO(" Remove User Info")
    }
}
