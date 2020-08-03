package com.example.live;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    //    public static final String DEFAULTPATH = "http://ipadlive.cntv.soooner.com/cctv_p2p_hdcctv6.m3u8";
    public static final String DEFAULTPATH = "http://alcdn.hls.xiaoka.tv/2020627/028/9ce/ehcErh_m7bcsa4mH/index.m3u8";
    TextView textView;
    MutableLiveData<String> nameEvent;
Handler handler =new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
        super.handleMessage(msg);
        test();
        sendEmptyMessageDelayed(0,1000);
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.btn_jump);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
        final TestViewModel testModel = ViewModelProviders.of(this).get(TestViewModel.class);
        nameEvent = testModel.getNameEvent();
        nameEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e("tag", "onChanged: -------s== " + s);
                textView.setText(s);
            }
        });
        handler.sendEmptyMessageDelayed(0,1000);

    }

    int i = 0;

    public void test() {
        i++;
        nameEvent.setValue(i + "");
    }

}
