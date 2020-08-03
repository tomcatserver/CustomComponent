package com.example.customview;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

public class PaintActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        ImageView imageView = findViewById(R.id.iv_svg);
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat =  AnimatedVectorDrawableCompat.create(this, R.drawable.animated);
        imageView.setImageDrawable(animatedVectorDrawableCompat);
        Animatable animatable = (Animatable) imageView.getDrawable();
        animatable.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}

