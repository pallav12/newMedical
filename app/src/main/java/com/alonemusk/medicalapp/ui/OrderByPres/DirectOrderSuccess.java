package com.alonemusk.medicalapp.ui.OrderByPres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alonemusk.medicalapp.FirstActivity;
import com.alonemusk.medicalapp.R;

public class DirectOrderSuccess extends AppCompatActivity {
    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_order_success);

    }
    public void close(View v){
        startActivity(new Intent(DirectOrderSuccess.this,FirstActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
      startActivity(new Intent(DirectOrderSuccess.this,FirstActivity.class));
      finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void reload() {
        Intent intent =getIntent();
       overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
   finish();
       overridePendingTransition(0, 0);
        startActivity(intent);
    }
}

