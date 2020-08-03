package com.example.java;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

public class MyTextView extends TextView {
    private Context mContext;
    private Rect mRect = new Rect();
    private Paint mPaint = new Paint();
    private String preText;

    public MyTextView(Context context) {
        super(context);
        init(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (TextUtils.isEmpty(preText)) {
            super.onDraw(canvas);
        } else {
            mPaint.getTextBounds(preText, 0, preText.length(), mRect);
            int x = getWidth() / 2 - mRect.width() / 2;
            int y = getHeight() / 2 + mRect.height() / 2;
            Log.e("tag", "onDraw: ---x=" + x + ",y=" + y + ",preText=" + preText);
            canvas.drawText(preText, x, y, mPaint);
        }

    }

    private void init(Context context) {
        mContext = context;
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.LEFT);
        float size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
        mPaint.setTextSize(size);
    }

    public void setText(String text) {
        preText = text;
        invalidate();
    }
}
