package com.example.bloodbuddy_mvvm.view.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.location.LocationManagerCompat.requestLocationUpdates
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.bloodbuddy_mvvm.R
import com.example.bloodbuddy_mvvm.databinding.FragmentHomeBinding
import com.example.bloodbuddy_mvvm.viewmodel.HomeViewModel
import com.example.bloodbuddy_mvvm.viewmodel.LocationViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.jar.Manifest

class HomeFragment : Fragment() , OnMapReadyCallback {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var locationViewModel: LocationViewModel
    private var _binding: FragmentHomeBinding? = null
    private val LOCATION_PERMISSION_REQUEST_CODE = 2000
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textHome
        /*homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
       // prepRequestLocationUpdates()
        val newreqlayout: LinearLayout = binding.newrequest
        newreqlayout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.nav_host_fragment_activity_main,RequestFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()
            }
        })
        val nearbylayout: LinearLayout = binding.nearby
        nearbylayout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.nav_host_fragment_activity_main,NearbyFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()
            }
        })
        val bloodreqlayout: LinearLayout = binding.donateblood
        bloodreqlayout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.nav_host_fragment_activity_main,BloodRequestFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()
            }
        })


        val mapFragment = activity?.supportFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return root
    }

    private fun prepRequestLocationUpdates() {
        if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            requestLocationUpdates()
        } else{
            val permissionRequest = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
            this.requestPermissions(permissionRequest,LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private fun requestLocationUpdates() {
        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        locationViewModel.getLocationLiveData().observe(viewLifecycleOwner, Observer {
            latitude = it.latitude.toDouble()
            latitude = it.longitude.toDouble()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onMapReady(googleMap:GoogleMap){
        //super.onViewCreated(view, SavedInstanceState)
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(latitude, longitude))
        )


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prepRequestLocationUpdates()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdates()
                }
                else{
                    Toast.makeText(context,"Unable to update location without permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}