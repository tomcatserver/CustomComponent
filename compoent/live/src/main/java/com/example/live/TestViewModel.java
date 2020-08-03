package com.example.live;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestViewModel extends ViewModel{
    private MutableLiveData<String> mNameEvent = new MutableLiveData<>();

    public MutableLiveData<String> getNameEvent() {
        return mNameEvent;
    }
}
