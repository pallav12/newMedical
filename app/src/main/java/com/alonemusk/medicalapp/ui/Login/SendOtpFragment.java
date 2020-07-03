package com.alonemusk.medicalapp.ui.Login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.TaskExecutor;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.utils.Constants;
import com.alonemusk.medicalapp.ui.utils.PrefManager;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static com.android.volley.VolleyLog.TAG;

public class SendOtpFragment extends Fragment {

    private SendOtpViewModel mViewModel;
     ProgressBar progressBar;

    public static SendOtpFragment newInstance() {
        return new SendOtpFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.send_otp_fragment, container, false);
         progressBar=v.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);
        final EditText editText = v.findViewById(R.id.editText);
        Button button = v.findViewById(R.id.sendOtp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.progress.postValue(true);
                PrefManager.INSTANCE.putString(Constants.PHONE,"+91"+editText.getText().toString());
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+editText.getText().toString(), // Phone number to verify
                        60, // Timeout duration
                        TimeUnit.SECONDS, // Unit of timeout
                        TaskExecutors.MAIN_THREAD, // Activity (for callback binding)
                        mCallbacks); // OnVerificationStateChangedCallbacks

            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(SendOtpViewModel.class);
        mViewModel.progress.setValue(false);
        mViewModel.progress.observe(requireActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    progressBar.setVisibility(View.VISIBLE);
                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            mViewModel.progress.postValue(false);
            mViewModel.mCredential.postValue(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            mViewModel.progress.postValue(false);
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(getContext(), "Invalid credential", Toast.LENGTH_LONG).show();

            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(getContext(), "Too many requests", Toast.LENGTH_LONG).show();

            }
        }

        @Override
        public void onCodeSent(@NonNull String verificationId,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            Log.d(TAG, "onCodeSent:" + verificationId);
            mViewModel.progress.postValue(false);
            mViewModel.codeSent.setValue(true);
            mViewModel.mVerificationId = verificationId;

        }
    };
}
