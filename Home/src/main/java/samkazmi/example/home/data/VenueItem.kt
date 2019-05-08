package samkazmi.example.home.data

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VenueItem(var id: Int , var name : String, var image : String, var location: LatLng , var isOpen : Boolean , var todayTiming : String , var type : Int , var distance : Double, var duration : Double) : Parcelable, ClusterItem {
    override fun getPosition(): LatLng {
        return location
    }

    override fun getTitle(): String {
        return name
    }

    override fun getSnippet(): String {
        return ""
    }
}
