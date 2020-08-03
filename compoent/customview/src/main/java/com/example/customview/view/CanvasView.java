package com.example.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.customview.R;

import androidx.annotation.Nullable;

public class CanvasView extends View {
    private Paint mPaint = new Paint();
    private Bitmap mBitmap;

    public CanvasView(Context context) {
        super(context);
        init(context);
    }


    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(100);
        mBitmap = getBitmap(context, R.mipmap.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.RED);
//        canvas.drawLine(100, 100, 600, 600, mPaint);
//        canvas.drawPoint(100, 100, mPaint);
//        canvas.drawPoints(new float[]{100, 100, 200, 200, 300, 300}, 0, 6, mPaint);
//    canvas.drawRect(new Rect(100,200,300,600),mPaint);
//        drawPath(canvas);
//        canvas.drawOval(new RectF(200,200,300,400),mPaint);
//        canvas.drawCircle(400,800,200,mPaint);
//        canvas.drawText(new char[]{'1','2','3','4','5'},0,5,200,200,mPaint);
//        canvas.drawText("12345", 300, 300, mPaint);
//        canvas.drawText("12345",0,5,400,400,mPaint);
//        drawTextOnPath(canvas);
//        canvas.drawArc(new RectF(100, 100, 500, 500), 0, 090, true, mPaint);
//        drawRoundRect(canvas);
//        drawRotate(canvas);
//        drawSkew(canvas);
//        drawMatrix(canvas);
//        drawClip(canvas);
        saveCanvas(canvas);

    }

    private void saveCanvas(Canvas canvas) {
        RectF rect = new RectF(300, 300, 700, 700);
        canvas.drawRect(rect, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("1.原始", 400f, 600f, mPaint);


        RectF rectF = new RectF(100, 100, 500, 500);
        canvas.save();
        canvas.clipRect(rectF);
        mPaint.setColor(Color.BLUE);
        canvas.drawColor(mPaint.getColor());
        mPaint.setColor(Color.WHITE);
        canvas.drawText("2.clip", 200, 200, mPaint);

        canvas.restore();
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(300f, 300f, 600f, 600, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("3.裁剪之后", 350, 400, mPaint);
    }

    private void drawClip(Canvas canvas) {
        RectF rect = new RectF(300, 300, 700, 700);
        canvas.drawRect(rect, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("1.原始", 400, 600, mPaint);
        RectF rectF = new RectF(100f, 100f, 500f, 500f);
        canvas.clipRect(rectF);


        mPaint.setColor(Color.BLUE);
        canvas.drawColor(mPaint.getColor());
        mPaint.setColor(Color.WHITE);
        canvas.drawText("2.clip", 200, 200, mPaint);

        mPaint.setColor(Color.YELLOW);
        mPaint.setAlpha(100);
        canvas.drawRect(rect, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText("3.裁剪之后", 350, 400, mPaint);


    }

    private void drawMatrix(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 100, 100, mPaint);

        Matrix matrix = new Matrix();
        matrix.setTranslate(500, 500);
        canvas.drawBitmap(mBitmap, matrix, mPaint);

        Matrix matrix1 = new Matrix();
        matrix1.setScale(0.5f, 0.5f, 800, 800);
        canvas.drawBitmap(mBitmap, matrix1, mPaint);

        Matrix matrix2 = new Matrix();
        matrix2.setRotate(70, 1000, 800);
        canvas.drawBitmap(mBitmap, matrix2, mPaint);

        Matrix matrix3 = new Matrix();
        matrix3.setSkew(0.5f, 0.5f, 400, 400);
        canvas.drawBitmap(mBitmap, matrix3, mPaint);
    }

    private void drawSkew(Canvas canvas) {
        RectF rectF = new RectF(200, 200, 500, 500);
        mPaint.setAlpha(100);
        canvas.rotate(45f, 200, 200);
        canvas.drawRoundRect(rectF, 100, 100, mPaint);


        canvas.skew(0, 0.5f);
        mPaint.setColor(Color.BLUE);
        canvas.drawRoundRect(rectF, 100, 100, mPaint);

    }

    private void drawRotate(Canvas canvas) {
        RectF rectF = new RectF(200, 200, 500, 500);
        mPaint.setAlpha(100);
        canvas.rotate(45f, 200, 200);
        canvas.drawRoundRect(rectF, 100, 100, mPaint);

    }

    private void drawRoundRect(Canvas canvas) {
        RectF rectF = new RectF(100, 100, 500, 500);
        canvas.drawRoundRect(rectF, 50, 50, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.translate(510, 0);
        canvas.drawRoundRect(rectF, 50, 50, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.scale(0.5f, 0.5f);
        canvas.drawRoundRect(rectF, 50, 50, mPaint);

    }

    private void drawTextOnPath(Canvas canvas) {
        mPaint.setAlpha(100);
        Path path = new Path();
        path.moveTo(300, 300);
        path.lineTo(300, 500);
        path.lineTo(500, 800);
        path.lineTo(800, 200);
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.drawTextOnPath("adldldldldldsaaskxklclclxlkxkdkkdkdds", path, 0, 100, mPaint);
    }

    private void drawPath(Canvas canvas) {
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(200, 200);
        path.lineTo(100, 200);
        path.lineTo(100, 100);
        path.close();
        canvas.drawPath(path, mPaint);

        Path path2 = new Path();
        path2.moveTo(100, 600);
        RectF rectF = new RectF(50, 50, 300, 400);
        path2.arcTo(rectF, 0, 90);
        canvas.drawPath(path2, mPaint);
    }

    private static Bitmap getBitmap(Context context, int vectorDrawableId) {
        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
        }
        return bitmap;
    }
}
