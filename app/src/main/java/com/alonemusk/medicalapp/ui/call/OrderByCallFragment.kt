package com.alonemusk.medicalapp.ui.call

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders

import com.alonemusk.medicalapp.R

class OrderByCallFragment : Fragment() {
    private lateinit var callNowViewModel: CallNowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v=inflater.inflate(R.layout.fragment_order_by_call, container, false)
        val callNow:Button=v.findViewById(R.id.bt_call_now)
        val requestCall:Button=v.findViewById(R.id.bt_request_call)
        callNow.setOnClickListener{
            callNowViewModel.fragmentTransaction.postValue(CallNowFragment())
        }
        requestCall.setOnClickListener{
            callNowViewModel.fragmentTransaction.postValue(RequestCallFragment())
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callNowViewModel=ViewModelProviders.of(requireActivity()).get(CallNowViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                OrderByCallFragment().apply {

                }
    }
}
