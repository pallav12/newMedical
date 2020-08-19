package com.alonemusk.medicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.alonemusk.medicalapp.ui.new_home.NewHomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(getApplicationContext(), NewHomeActivity.class);
        startActivity(intent);
        finish();
    }
}
