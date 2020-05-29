package com.example.kotlin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import androidx.annotation.Nullable;

@Route(path = "/kotlin/com/example/MainActivity")
public class TestActivity extends Activity {
    @Autowired
    public String keyKotlin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        ARouter.getInstance().inject(this);
        Log.e("tag", "onCreate: ------com/example/kotlin/MainActivity---keyKotlin=" + keyKotlin);
    }
}
