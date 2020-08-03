package com.example.video;

import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import java.io.IOException;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class VideoPlayer extends FrameLayout {
    private IMediaPlayer mMediaPlayer;
    private String mPath;
    private Map<String, String> mHeader;
    private SurfaceView mSurfaceView;
    private Context mContext;
    private boolean mEnableMediaCodec;
    private AudioManager mAudioManager;

    public VideoPlayer(@NonNull Context context) {
        super(context);
        init(context);
    }

    public VideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public VideoPlayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {
        mContext = context;
        setBackgroundColor(Color.BLACK);
        createSurfaceView();
        mAudioManager = (AudioManager) mContext.getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
    }

    private void createSurfaceView() {
        mSurfaceView = new SurfaceView(mContext);
        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.e("tag", "surfaceCreated:------ ");
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.e("tag", "surfaceChanged:------ format=" + format + ",width=" + width + ",height=" + height);
                if (mMediaPlayer != null) {
                    mMediaPlayer.setDisplay(holder);
                }

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.e("tag", "surfaceDestroyed:------ ");

            }
        });
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER);
        addView(mSurfaceView, 0, layoutParams);

    }

    private IMediaPlayer createPlayer() {
        IjkMediaPlayer player = new IjkMediaPlayer();
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 1);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "http-detect-range-support", 1);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "min-frames", 100);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "enable-accurate-seek", 1);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "dns_cache_clear", 1);
        player.setVolume(1.0f, 1.0f);
        setEnableMediaCodec(player, mEnableMediaCodec);
        return player;
    }

    private void setEnableMediaCodec(IjkMediaPlayer player, boolean isEnable) {
        int value = isEnable ? 1 : 0;
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", value); //开启硬编码
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-auto-rotate", value);
        player.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec-handle-resolution-change", value);
    }

    public void setListener() {
        mMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.e("tag", "onPrepared: ------iMediaPlayer");
            }
        });

        mMediaPlayer.setOnVideoSizeChangedListener(new IMediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
                Log.e("tag", "onVideoSizeChanged: -----i=" + i + ",i1=" + i1 + ",i2=" + i2 + ",i3=" + i3);
            }
        });

        mMediaPlayer.setOnCompletionListener(new IMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.e("tag", "onCompletion: -----");
            }
        });
        mMediaPlayer.setOnErrorListener(new IMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.e("tag", "onError: ---i=" + i + ",i1=" + i1);
                return false;
            }
        });
        mMediaPlayer.setOnSeekCompleteListener(new IMediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.e("tag", "onSeekComplete: ----");
            }
        });


    }

    //设置播放地址
    public void setPath(String path) {
        setPath(path, null);
    }

    public void setPath(String path, Map<String, String> header) {
        mPath = path;
        mHeader = header;
    }

    public void load() throws IOException {
        if(mMediaPlayer !=null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        mMediaPlayer =createPlayer();
        setListener();
        mMediaPlayer.setDisplay(mSurfaceView.getHolder());
        mMediaPlayer.setDataSource(mContext, Uri.parse(mPath),mHeader);
        mMediaPlayer.prepareAsync();
        start();
    }


    public void start() {
        if (mMediaPlayer != null) {
            mMediaPlayer.start();
//            mAudioFocusHelper.requestFocus();
        }
    }

    public void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
//            mAudioFocusHelper.abandonFocus();
        }
    }

    public void pause() {
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
//            mAudioFocusHelper.abandonFocus();
        }
    }

    public void stop() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
//            mAudioFocusHelper.abandonFocus();
        }
    }


    public void reset() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
//            mAudioFocusHelper.abandonFocus();
        }
    }


    public long getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        } else {
            return 0;
        }
    }


    public long getCurrentPosition() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getCurrentPosition();
        } else {
            return 0;
        }
    }


    public void seekTo(long l) {
        if (mMediaPlayer != null) {
            mMediaPlayer.seekTo(l);
        }
    }

    public boolean isPlaying(){
        if(mMediaPlayer != null) {
            return mMediaPlayer.isPlaying();
        }
        return false;
    }

}
