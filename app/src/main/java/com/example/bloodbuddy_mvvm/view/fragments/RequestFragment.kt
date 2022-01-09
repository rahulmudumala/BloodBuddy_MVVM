package com.example.bloodbuddy_mvvm.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bloodbuddy_mvvm.R
import com.example.bloodbuddy_mvvm.viewmodel.RequestViewModel

class RequestFragment : Fragment() {

    companion object {
        fun newInstance() = RequestFragment()
    }

    private lateinit var viewModel: RequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.request_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RequestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}