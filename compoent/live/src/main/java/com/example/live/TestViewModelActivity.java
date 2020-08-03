package com.example.live;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class TestViewModelActivity extends FragmentActivity implements ViewModelStoreOwner, LifecycleOwner {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_model_main);
       final TextView textView= findViewById(R.id.tv_view);
//        ViewModelProvider provider =  ViewModelProviders(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        final TestViewModel testModel =ViewModelProviders.of(this).get(TestViewModel.class);
        final MutableLiveData<String> nameEvent = testModel.getNameEvent();
        nameEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e("tag", "onChanged: -------s== " + s);
                textView.setText(s);
            }
        });

    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }
}
