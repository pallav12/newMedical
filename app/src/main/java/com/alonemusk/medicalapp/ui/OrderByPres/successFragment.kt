package com.alonemusk.medicalapp.ui.OrderByPres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.utils.Constants
import com.alonemusk.medicalapp.ui.utils.PrefManager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [successFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class successFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_success, container, false)
        val button:ImageView=v.findViewById(R.id.close)
        PrefManager.putBoolean(Constants.PREF_SUBMITTED,true)
        button.setOnClickListener {
            activity?.finish()
        }
        return v
    }

    companion object {

        @JvmStatic fun newInstance(param1: String, param2: String) =
                successFragment().apply {
                }
    }
}
