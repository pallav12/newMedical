package com.alonemusk.medicalapp.ui.utils;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.alonemusk.medicalapp.ui.models.CallRequest;
import com.alonemusk.medicalapp.ui.models.Doctor;
import com.alonemusk.medicalapp.ui.models.OrdersByPres;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class FirebaseUtils {
    public static void getDoctor(final listener<Doctor> listener){
        FirebaseDatabase.getInstance().getReference("calls").child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for( DataSnapshot a:dataSnapshot.getChildren()){
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
    public static void uploadOrderByPres(final OrdersByPres order, Uri uri, final listener<String> listener){
        FirebaseStorage.getInstance().getReference(order.getOrderId()).child(order.getTimestamp()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                FirebaseDatabase.getInstance().getReference("ordersByPres").push().setValue(order).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.onSuccess("Yo");
                    }
                });
            }
        });
    }
    public static void requestPhone(String phone, String message, final listener<String>listener) {
        FirebaseDatabase.getInstance().getReference("calls").child("requests").push().setValue(new CallRequest(phone, message, Utils.Companion.getTime())).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                listener.onSuccess("Request successfully send");
            }
        });
    }
    public interface  listener<T>{
        public void onSuccess(T it);
        public void onError(DatabaseError it);
    }
}

