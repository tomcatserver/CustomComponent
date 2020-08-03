package com.example.plugin;

import android.util.Log;

public class TestProxy implements IProxyInterface {
    @Override
    public void startActivity(String s) {
        Log.e("tag", "TestProxy-----startActivity:----s= " + s);
    }
}
