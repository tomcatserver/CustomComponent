package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SVGView extends View {
    public SVGView(Context context) {
        super(context);
        init(context);

    }

    public SVGView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public SVGView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
    }


}
