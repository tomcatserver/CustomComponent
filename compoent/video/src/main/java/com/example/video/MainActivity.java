package com.example.video;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private VideoPlayer videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoPlayer = findViewById(R.id.vp_video);
        findViewById(R.id.tv_load_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlayer.setPath("http://alcdn.hls.xiaoka.tv/2018829/28a/e72/5pPiTaJrPWcWvUKY/index.m3u8");
                try {
                    videoPlayer.load();

                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "播放失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
