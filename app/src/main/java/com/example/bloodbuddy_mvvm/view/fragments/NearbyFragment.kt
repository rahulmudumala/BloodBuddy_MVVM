package com.example.bloodbuddy_mvvm.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bloodbuddy_mvvm.R
import com.example.bloodbuddy_mvvm.viewmodel.NearbyViewModel

class NearbyFragment : Fragment() {

    companion object {
        fun newInstance() = NearbyFragment()
    }

    private lateinit var viewModel: NearbyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nearby_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NearbyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}