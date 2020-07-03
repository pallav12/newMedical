package com.alonemusk.medicalapp.ui.call

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.models.Doctor
import com.alonemusk.medicalapp.ui.utils.FirebaseUtils
import com.google.firebase.database.DatabaseError
import kotlinx.android.synthetic.main.call_now_fragment.*


class CallNowFragment : Fragment() {
    companion object {
        fun newInstance() = CallNowFragment()
    }

    private lateinit var viewModel: CallNowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.call_now_fragment, container, false)
        val name: TextView = v.findViewById(R.id.tv_name)
        val phone: TextView = v.findViewById(R.id.tv_phone)
        val callNow: Button = v.findViewById(R.id.call_now)
        FirebaseUtils.getDoctor(object : FirebaseUtils.listener<Doctor> {
            override fun onSuccess(it: Doctor) {
                name.text = it.name
                phone.text = it.phone
                viewModel.progress.postValue(false)
            }

            override fun onError(databaseError: DatabaseError) {
            }

        })
        callNow.setOnClickListener {
           makeCall();
        }
        return v
    }

    private fun makeCall() {
        if (ContextCompat.checkSelfPermission(requireActivity(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CALL_PHONE), 0)
        } else {
            val dial = "tel:${tv_phone.text}"
            startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(CallNowViewModel::class.java)
        viewModel.progress.postValue(true)

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == 0) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makeCall()
            } else {
                Toast.makeText(requireContext(), "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
