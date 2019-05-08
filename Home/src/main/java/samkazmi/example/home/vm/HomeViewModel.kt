package samkazmi.example.home.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import samkazmi.example.base.BaseViewModel
import samkazmi.example.base.utils.SingleLiveEvent
import samkazmi.example.home.R
import samkazmi.example.home.data.Menu
import samkazmi.example.home.data.VenueItem
import javax.inject.Inject

class HomeViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    val menuList: MutableList<Menu> = ArrayList()
    val venueListSmall = MutableLiveData<MutableList<VenueItem>>(ArrayList())
    val venueListBig = MutableLiveData<MutableList<VenueItem>>(ArrayList())
    private val currentLatLng = SingleLiveEvent<LatLng>()
    fun getCurrentLatLng(): SingleLiveEvent<LatLng> {
        return currentLatLng
    }

    fun setCurrentLatLng(currentLatLng: LatLng) {
        this.currentLatLng.value = currentLatLng
    }

    init {
        setupMenuList()
        this.venueListBig.value = data()
    }

    private fun setupMenuList() {
        menuList.add(Menu(1, R.drawable.ic_profile, getString(R.string.my_profile)))
        menuList.add(Menu(2, R.drawable.ic_order, getString(R.string.past_purchases)))
        menuList.add(Menu(3, R.drawable.ic_learn, getString(R.string.learn_how_to)))
        menuList.add(Menu(4, R.drawable.ic_payment_, getString(R.string.payment_details)))
        menuList.add(Menu(5, R.drawable.ic_settings, getString(R.string.settings)))
    }

    fun loadList(currentMapCenter: LatLng) {
        setupVenueList()
    }

    fun clearList() {
        this.venueListSmall.value = ArrayList()
    }

    private fun setupVenueList() {
        this.venueListSmall.value = data()
    }

    private fun data(): MutableList<VenueItem> {
        val venueListSmall: MutableList<VenueItem> = ArrayList()
        venueListSmall.add(VenueItem(1, "The Gaming Store", "", LatLng(-24.939694, 134.338103), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(2, "The Gaming Store", "", LatLng(-24.026748, 133.642099), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(3, "The Gaming Store", "", LatLng(-23.191173, 133.729400), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(4, "The Gaming Store", "", LatLng(-22.161699, 133.415156), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(5, "The Gaming Store", "", LatLng(-21.500665, 133.883339), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(6, "The Gaming Store", "", LatLng(-20.637630, 134.191054), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(7, "The Gaming Store", "", LatLng(-19.005472, 134.111607), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(8, "The Gaming Store", "", LatLng(-21.549141, 128.948053), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(9, "The Gaming Store", "", LatLng(-23.541874, 129.010437), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(10, "The Gaming Store", "", LatLng(-25.301472, 128.987437), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(11, "The Gaming Store", "", LatLng(-27.026785, 129.044929), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(12, "The Gaming Store", "", LatLng(-22.007586, 139.542323), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(13, "The Gaming Store", "", LatLng(-24.724202, 139.603771), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(14, "The Gaming Store", "", LatLng(-22.668094, 136.524340), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        venueListSmall.add(VenueItem(15, "The Gaming Store", "", LatLng(-21.865866, 122.136369), true, "Open Until 9 pm", 1, 12.toDouble(), 12.toDouble()))
        return venueListSmall
    }
}
