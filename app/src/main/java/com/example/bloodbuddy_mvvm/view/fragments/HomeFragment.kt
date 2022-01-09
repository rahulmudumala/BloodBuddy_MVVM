package com.example.bloodbuddy_mvvm.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bloodbuddy_mvvm.R
import com.example.bloodbuddy_mvvm.databinding.FragmentHomeBinding
import com.example.bloodbuddy_mvvm.viewmodel.HomeViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HomeFragment : Fragment() , OnMapReadyCallback {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

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
        val newreqlayout: LinearLayout = binding.newrequest
        newreqlayout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.container,RequestFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()
            }
        })
        val nearbylayout: LinearLayout = binding.nearby
        nearbylayout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.container,NearbyFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()
            }
        })
        val bloodreqlayout: LinearLayout = binding.donateblood
        bloodreqlayout.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.container,BloodRequestFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()
            }
        })


        val mapFragment = activity?.supportFragmentManager?.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onMapReady(googleMap:GoogleMap){
        //super.onViewCreated(view, SavedInstanceState)
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )


    }

}