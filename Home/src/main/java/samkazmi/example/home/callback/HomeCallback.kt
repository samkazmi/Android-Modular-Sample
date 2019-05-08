package samkazmi.example.home.callback

import samkazmi.example.home.data.VenueItem

interface HomeCallback {
    fun openMenu()
    fun showOnboardingScreen()
    fun showVenueDetail(venueItem: VenueItem)
    fun requestLocation()
}
