package com.alonemusk.medicalapp.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alonemusk.medicalapp.MainActivity;
import com.alonemusk.medicalapp.R;
import com.alonemusk.medicalapp.ui.utils.General;
import com.alonemusk.medicalapp.ui.utils.PrefManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            General.Companion.toast("Authentication");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        if(PrefManager.INSTANCE.getBoolean(R.string.first_time,true)){
            startActivity(new Intent(this,FlashActivity.class));
            finish();
        }
        SendOtpViewModel sendOtpViewModel = ViewModelProviders.of(this).get(SendOtpViewModel.class);
        sendOtpViewModel.mCredential.observe(this, new Observer<PhoneAuthCredential>() {
            @Override
            public void onChanged(PhoneAuthCredential credential) {
                signInWithPhoneCredential(credential);
            }
        });

        sendOtpViewModel.codeSent.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new ConfirmOtpFragment()).commit();
                }
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.container, new SendOtpFragment()).commit();
    }

    private void signInWithPhoneCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Verification Automatically completed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(getApplicationContext(), "Invalid application code", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }
}
