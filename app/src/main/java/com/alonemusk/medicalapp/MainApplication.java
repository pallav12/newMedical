package com.alonemusk.medicalapp;

import android.app.Application;

public class MainApplication extends Application{
    public static Application application;
    public static Application getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }
}
