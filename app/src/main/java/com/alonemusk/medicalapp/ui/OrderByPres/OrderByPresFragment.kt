package com.alonemusk.medicalapp.ui.OrderByPres

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.alonemusk.medicalapp.R
import com.alonemusk.medicalapp.ui.models.OrdersByPres
import com.alonemusk.medicalapp.ui.utils.FirebaseUtils
import com.alonemusk.medicalapp.ui.utils.Utils
import com.google.firebase.database.DatabaseError
import java.io.FileNotFoundException
import java.io.InputStream


class OrderByPresFragment : Fragment(), FirebaseUtils.listener<String> {
    lateinit var view: ImageView
    var uri: Uri? = null
    lateinit var viewModel: OrderByPresViewmodel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_order_by_pres, container, false)
        val button: View = v.findViewById(R.id.upload_prescription)
        button.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, 1)
        }
        view = v.findViewById(R.id.presImage)
        val submit: Button = v.findViewById(R.id.submit_order)
        val editText: EditText = v.findViewById(R.id.message)
        submit.setOnClickListener {
            val message = editText.text.toString()
            when {
                uri == null -> {
                    Utils.toast("Nothing selected")
                }
                message.length<10 -> {
                    Utils.toast("More description needed")
                }
                viewModel.progress.value as Boolean -> {
                    Utils.toast("upload in progress")
                }
                else -> {
                    viewModel.progress.postValue(true)
                    FirebaseUtils.uploadOrderByPres(OrdersByPres(Utils.getTime(), Utils.getTime(), "user", message), uri, this)
                }
            }
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(OrderByPresViewmodel::class.java)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                OrderByPresFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            try {
                val imageUri: Uri = data?.data as Uri
                val imageStream: InputStream = requireActivity().getContentResolver().openInputStream(imageUri) as InputStream
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                uri = imageUri
                view.setImageBitmap(selectedImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSuccess(it: String?) {
        Utils.toast("Order submitted")
        viewModel.fragmentTransaction.postValue(successFragment())
        viewModel.progress.postValue(false)
    }

    override fun onError(it: DatabaseError?) {
    }
}
