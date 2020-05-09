package com.alonemusk.medicalapp.ui.Login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class SendOtpViewModel extends ViewModel {

    public MutableLiveData<Boolean> codeSent = new MutableLiveData<>();
    public MutableLiveData<PhoneAuthCredential> mCredential = new MutableLiveData<>();
    public String mVerificationId;
    public MutableLiveData<Boolean> progress = new MutableLiveData<>();


    public void setCodeSent(String codeSent) {
        mCredential.setValue(PhoneAuthProvider.getCredential(mVerificationId, codeSent));
    }
}
