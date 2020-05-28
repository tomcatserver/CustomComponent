package com.example.common;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    public Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
