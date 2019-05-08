package samkazmi.example.datainterfaces.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Message
 */

@Parcelize
data class Message(
    @SerializedName("code") var code: Int? = null, @SerializedName("message")
    var message: String? = null
) : Parcelable



