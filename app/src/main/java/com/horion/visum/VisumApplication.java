package com.horion.visum;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class VisumApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
