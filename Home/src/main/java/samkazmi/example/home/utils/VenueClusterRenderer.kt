package samkazmi.example.home.utils

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import samkazmi.example.home.R
import samkazmi.example.home.data.VenueItem

class VenueClusterRenderer(context: Context, map: GoogleMap, clusterManager: ClusterManager<VenueItem>) : DefaultClusterRenderer<VenueItem>(context, map, clusterManager) {

    override fun shouldRenderAsCluster(cluster: Cluster<VenueItem>): Boolean {
        return cluster.size > 1
    }

    override fun onBeforeClusterItemRendered(item: VenueItem?, markerOptions: MarkerOptions?) {
        markerOptions?.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_pin))
    }

}