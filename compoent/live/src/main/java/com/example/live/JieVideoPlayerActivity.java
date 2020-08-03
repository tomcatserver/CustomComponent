package com.example.live;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class JieVideoPlayerActivity extends Activity {
    private VideoView mVideoView;
    private AudioManager mAudioManager;
    private int mMaxVolume;
    private String mPath;
    private MediaController mMediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this)) {
            Log.e("tag", "onCreate: -------没有找到 com.example.livelib.vitamio.activity.InitActivity");
            return;
        }
        setContentView(R.layout.activity_jie_video_player);
        mPath = getIntent().getStringExtra("path");
        mVideoView = findViewById(R.id.vv_surface_view);
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        if (mPath.startsWith("http:")) {
            mVideoView.setVideoURI(Uri.parse(mPath));
        } else {
            mVideoView.setVideoPath(mPath);
        }
        mMediaController = new MediaController(this);
        mMediaController.setFileName("明天真好！！！");
        mVideoView.setMediaController(mMediaController);
        mVideoView.requestFocus();

//        GestureDetector gestureDetector = new GestureDetector(this, new MyGestureDetector());
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
    }

    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
}
