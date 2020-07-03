package com.alonemusk.medicalapp.ui.utils;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alonemusk.medicalapp.ui.models.Doctor;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseUtils {
    public static void getDoctor(final listener<Doctor> listener){
        FirebaseDatabase.getInstance().getReference("calls").child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for( DataSnapshot a:dataSnapshot.getChildren()){
                    Log.d("hello",a.toString());
                    Doctor doctor=a.getValue(Doctor.class);
                    if(doctor.getAvailable()){
                        listener.onSuccess(doctor);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public interface  listener<T>{
        public void onSuccess(T it);
        public void onError(DatabaseError it);
    }
}

