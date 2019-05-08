package samkazmi.example.home.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.graphics.drawable.DrawableCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.SphericalUtil
import com.google.maps.android.clustering.ClusterManager
import samkazmi.example.base.BaseFragment
import samkazmi.example.base.RecyclerViewCallback
import samkazmi.example.base.utils.Constants.CAMERA_ZOOM_CURRENT_LOC
import samkazmi.example.base.utils.Constants.MAX_ZOOM
import samkazmi.example.home.R
import samkazmi.example.home.adapter.VenueAdapter
import samkazmi.example.home.adapter.VenuePagerAdapter
import samkazmi.example.home.callback.HomeCallback
import samkazmi.example.home.callback.HomeViewCallback
import samkazmi.example.home.data.VenueItem
import samkazmi.example.home.databinding.HomeFragmentBinding
import samkazmi.example.home.utils.VenueClusterRenderer
import samkazmi.example.home.vm.HomeViewModel


class HomeFragment : BaseFragment(), OnMapReadyCallback, HomeViewCallback, RecyclerViewCallback {

    private val TAG = HomeFragment::class.java.simpleName
    private lateinit var mMap: GoogleMap
    private var currentMapCenter: LatLng? = null
    private lateinit var mClusterManager: ClusterManager<VenueItem>
    private val vm: HomeViewModel by lazy {
        (activity as HomeActivity).getHomeViewModel()
    }
    private lateinit var binding: HomeFragmentBinding

    private val smallAdapter by lazy {
        VenuePagerAdapter(callback = this, mContext = requireContext())
    }

    private val bigAdapter = VenueAdapter(this)

    private val TRANSLATE_Y by lazy {
        resources.getDimension(R.dimen.max_translate_value)
    }

    private val MARGIN by lazy {
        resources.getDimension(R.dimen.venue_margin_bottom)
    }

    private var callback: HomeCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HomeCallback) {
            callback = context
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.callback = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvSmallList.adapter = smallAdapter
        binding.rvBigList.adapter = bigAdapter
        binding.rvBigList.visibility = View.GONE
        isBigListShowin = false

        vm.venueListBig.observe(this, Observer {
            bigAdapter.submitList(it)
        })

        callback?.requestLocation()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        DrawableCompat.setTint(binding.bCurrentLocation.drawable, Color.WHITE)
        binding.rvSmallList.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        binding.rvBigList.layoutManager = LinearLayoutManager(requireContext())

    }

    private var isSmallListShowin = false
    private var isBigListShowin = false
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.clear()
        mMap.setMaxZoomPreference(MAX_ZOOM)
        mMap.uiSettings.isMyLocationButtonEnabled = false
        mMap.uiSettings.isCompassEnabled = false
        mMap.uiSettings.isMapToolbarEnabled = false

        context?.let {
            mClusterManager = ClusterManager(context, mMap)
            mMap.setOnMarkerClickListener(mClusterManager)
            mMap.setOnInfoWindowClickListener(mClusterManager)
            /*mClusterManager.setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<Any> { venueMapItem ->
            })
            mClusterManager.setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<Any> { venueMapItem ->
                vm.setCurrentVenueMapItem(venueMapItem)
                false
            })*/
            mClusterManager.renderer = VenueClusterRenderer(it, mMap, mClusterManager)
            // mClusterManager.markerCollection?.setOnInfoWindowAdapter(infoWindow)
        }

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            if (context != null) {
                val success = googleMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                context, R.raw.map_style))

                if (!success) {
                    Log.e(TAG, "Style parsing failed.")
                }
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }



        mMap.setOnMarkerClickListener {
            if (!isSmallListShowin) {
                showSmallList()
            } else {
                hideSmallList()
            }
            TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
            true
        }


        mMap.setOnCameraIdleListener {
            val previousMapCenter = currentMapCenter
            currentMapCenter = mMap.cameraPosition.target
            if (previousMapCenter == null || SphericalUtil.computeDistanceBetween(currentMapCenter, previousMapCenter) > 5000) {
                currentMapCenter?.let {
                    vm.loadList(it)
                }
            }
        }
        moveMapToLocation(LatLng(-25.005881, 134.321670))

        vm.venueListSmall.observe(this, Observer {
            smallAdapter.updateList(it)
            mClusterManager.clearItems()
            mClusterManager.addItems(it)
            mClusterManager.cluster()
        })
    }

    private fun showSmallList() {
        isSmallListShowin = true

        binding.rvSmallList.animate().translationY(0f).setStartDelay(130).setInterpolator(FastOutSlowInInterpolator()).start()
        binding.bList.animate().translationY(0f).setInterpolator(FastOutSlowInInterpolator()).start()
        binding.bCurrentLocation.animate().translationY(0f).setInterpolator(FastOutSlowInInterpolator()).start()
        binding.rvSmallList.post {
            binding.rvSmallList.setCurrentItem((0..14).random(), true)
        }
    }

    private fun hideSmallList() {
        isSmallListShowin = false

        binding.rvSmallList.animate().translationY(TRANSLATE_Y).setInterpolator(FastOutSlowInInterpolator()).start()
        binding.bList.animate().translationY(TRANSLATE_Y - MARGIN).setInterpolator(FastOutSlowInInterpolator()).setStartDelay(130).start()
        binding.bCurrentLocation.animate().translationY(TRANSLATE_Y - MARGIN).setInterpolator(FastOutSlowInInterpolator()).setStartDelay(130).start()
    }

    override fun onCurrentLocationButtonClicked() {
        if (isSmallListShowin) {
            hideSmallList()
        }
        if (isBigListShowin) {
            hideBigList()
        }
        TransitionManager.beginDelayedTransition(binding.root as ViewGroup)

        // TODO clear map center position

        if (vm.getCurrentLatLng().value == null) {
            callback?.requestLocation()
        } else {
            moveMapToLocation(vm.getCurrentLatLng().value)
        }
    }

    override fun onListButtonClicked() {
        if (!isBigListShowin) {
            showBigList()
        } else {
            hideBigList()
        }
        TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
    }

    private fun showBigList() {
        if (isSmallListShowin) {
            hideSmallList()
        }
        binding.rvBigList.visibility = View.VISIBLE
        isBigListShowin = true
        binding.bList.setIconResource(R.drawable.ic_map_pin)
        binding.bList.setText(R.string.map)
    }

    private fun hideBigList() {
        binding.rvBigList.visibility = View.GONE
        isBigListShowin = false
        binding.bList.setIconResource(R.drawable.ic_list)
        binding.bList.setText(R.string.list)
    }

    override fun onMenuButtonClicked() {
        callback?.openMenu()
    }

    override fun onHowToButtonClicked() {
        callback?.showOnboardingScreen()
    }

    override fun onListItemClicked(position: Int) {

    }

    @SuppressLint("MissingPermission")
    fun onLocationChanged(latLng: LatLng) {
        vm.setCurrentLatLng(latLng)
        if (!mMap.uiSettings.isMyLocationButtonEnabled) {
            mMap.isMyLocationEnabled = true
        }

        // TODO UPDATE LIST
    }

    private fun moveMapToLocation(latLng: LatLng?) {
        if (latLng != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, CAMERA_ZOOM_CURRENT_LOC))
        }
    }
}
