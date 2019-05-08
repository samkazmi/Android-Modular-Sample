package samkazmi.example.home.vm

import android.app.Application
import androidx.databinding.ObservableField


import javax.inject.Inject

import samkazmi.example.base.BaseViewModel
import samkazmi.example.home.R

import samkazmi.example.home.ui.LocationSetupDialogFragment.Companion.TYPE_ALLOW_LOCATION_PERMISSION_DIALOG
import samkazmi.example.home.ui.LocationSetupDialogFragment.Companion.TYPE_ALLOW_LOCATION_PERMISSION_SETTING
import samkazmi.example.home.ui.LocationSetupDialogFragment.Companion.TYPE_LOCATION_SETTINGS_ON


class PermissionViewModel @Inject
constructor(application: Application) : BaseViewModel(application) {

    val message = ObservableField<String>()
    val positiveButtonText = ObservableField<String>()
    private var messageType = 0

    fun setMessageType(messageType: Int) {
        this.messageType = messageType
        updateText(messageType)

    }

    private fun updateText(messageType: Int) {
        when (messageType) {
            TYPE_ALLOW_LOCATION_PERMISSION_DIALOG, TYPE_ALLOW_LOCATION_PERMISSION_SETTING -> {
                message.set(getString(R.string._please_allow_location_permission))
                positiveButtonText.set(getString(R.string.allow_permission))
            }
            TYPE_LOCATION_SETTINGS_ON -> {
                message.set(getString(R.string._please_turn_on_your_location_from_settings))
                positiveButtonText.set(getString(R.string.turn_on_location))
            }
        }
    }
}
