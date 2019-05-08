package samkazmi.example.home.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import samkazmi.example.home.data.VenueItem

class VenueItemViewModel : ViewModel() {

     var venueItem = ObservableField<VenueItem>()

}