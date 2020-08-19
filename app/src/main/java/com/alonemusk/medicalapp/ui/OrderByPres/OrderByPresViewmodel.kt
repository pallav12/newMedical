package com.alonemusk.medicalapp.ui.OrderByPres

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class OrderByPresViewmodel(application: Application):AndroidViewModel(application) {
    val fragmentTransaction=MutableLiveData<Fragment>()
    val progress=MutableLiveData<Boolean>()
    init {
        fragmentTransaction.postValue(OrderByPresFragment())
        progress.postValue(false)
    }
}