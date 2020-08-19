package com.alonemusk.medicalapp.ui.call

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders

import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.utils.FirebaseUtils
import com.alonemusk.medicalapp.ui.utils.Utils
import com.alonemusk.medicalapp.ui.utils.PrefManager
import com.google.firebase.database.DatabaseError

class RequestCallFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var send:Button
    private lateinit var message:EditText
    private lateinit var phone:EditText
    private lateinit var viewModel: CallNowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_request_call, container, false)
        send=v.findViewById(R.id.submit)
        message=v.findViewById(R.id.message)
        phone=v.findViewById(R.id.et_phone)
        phone.setText(PrefManager.getPhone())
        send.setOnClickListener {
            viewModel.progress.postValue(true)
            FirebaseUtils.requestPhone(phone.text.toString(), message.text.toString(),object :FirebaseUtils.listener<String>{
                override fun onSuccess(it: String) {
                    Utils.toast(it)
                    viewModel.progress.postValue(false)
                    activity!!.finish()
                }

                override fun onError(it: DatabaseError?) {
                    TODO("Not yet implemented")
                }

            })
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel=ViewModelProviders.of(requireActivity()).get(CallNowViewModel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RequestCallFragment().apply {
                }
    }
}
