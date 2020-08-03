package com.example.live;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import androidx.annotation.Nullable;

@Route(path = "/livemoudle/com/example/TestActivity")

public class TestActivity extends Activity {
    @Autowired
    public String keyJava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        ARouter.getInstance().inject(this);
        Log.e("tag", "onCreate:---keyJava= " + keyJava + ",/livemoudle/com/example/TestActivity=" + this);
    }
}