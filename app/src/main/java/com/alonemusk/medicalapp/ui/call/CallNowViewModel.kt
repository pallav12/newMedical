package com.alonemusk.medicalapp.ui.call

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CallNowViewModel : ViewModel() {
    public val fragmentTransaction=MutableLiveData<Fragment>()
    public val progress=MutableLiveData<Boolean>()
}
