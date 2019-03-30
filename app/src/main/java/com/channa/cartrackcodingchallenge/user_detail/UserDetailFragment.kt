package com.channa.cartrackcodingchallenge.user_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.channa.cartrackcodingchallenge.R
import com.channa.cartrackcodingchallenge.data.source.remote.response.UserResponse
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.user_detail.view.*


/**
 * A fragment representing a single User detail screen.
 * This fragment is either contained in a [UserListActivity]
 * in two-pane mode (on tablets) or a [UserDetailActivity]
 * on handsets.
 */
class UserDetailFragment : Fragment(), OnMapReadyCallback {

    var googleMap: GoogleMap? = null

    override fun onMapReady(p0: GoogleMap?) {
        var userLatLng = LatLng(userResponse!!.address.geo.lat, userResponse!!.address.geo.lng)

        googleMap = p0
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        googleMap?.addMarker(MarkerOptions().position(userLatLng))
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 10f))
    }

    /**
     * This fragment will be displaying User details object
     */
    private var userResponse: UserResponse? = null

    lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(BUNDLE_KEY_USER)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                userResponse = it.getParcelable(BUNDLE_KEY_USER)
                activity?.toolbar_layout?.title = userResponse?.name
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.user_detail, container, false)

        // Gets the MapView from the XML layout and creates it
        mapView = rootView.findViewById(R.id.mv_user_location)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this) //this is important


        // Show the dummy content as text in a TextView.
        userResponse?.let {
            rootView.tv_name.text = it.name
            rootView.tv_username.text = it.username
            rootView.tv_email.text = it.email
            rootView.tv_address_suite.text = it.address.suite
            rootView.tv_address_street.text = it.address.street
            rootView.tv_address_city.text = it.address.city
            rootView.tv_address_zip_code.text = it.address.zipcode
        }

        return rootView
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    companion object {
        /**
         * The fragment argument representing the user object that this fragment
         * represents.
         */
        const val BUNDLE_KEY_USER = "bundle_user"
    }
}
