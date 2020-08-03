package com.example.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.customview.R;

import androidx.annotation.Nullable;

public class PaintView extends View {
    Paint mPaint = new Paint();
    private Bitmap mBitmap;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private int delta = 15;
    private int mTranslate = 0;
    private int mViewWidth;
    private LinearGradient mLinearGradient;
    private Matrix mGradientatrix;
    private Bitmap mBitmapScale;
    private ShapeDrawable mShapeDrawable;
    private Matrix mMatrix;
    private RectF mRectF;
    private int showHeight;
    private float mTextSize = 25f;

    public PaintView(Context context) {
        super(context);
        init(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
//        setShadowLayer();
//        setGradient(context);
//        setLinearGradient(context);
//        setRadialGradient(context);
//        setSweepGradient(context);
//        setComposeShader(context);
//        setHeartShader(context);
//        setZoomImageShader(context);
//        setFilterShader(context);
//        setXfermode(context);
//        String text = "这是一首简单的小情歌";
//       setTextColor(Color.RED);
//        setText(text);
    }

    private void setXfermode(Context context) {
        if (Build.VERSION.SDK_INT > 11) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStrokeWidth(2f);
    }

    private void setFilterShader(Context context) {
        mBitmap = getBitmap(context, R.mipmap.ic_launcher);
        ColorMatrix colorMatrix = new ColorMatrix(new float[]{1.2f, 0, 0, 0, 0,
                0, 1.2f, 0, 0, 0,
                0, 0, 1.2f, 0, 0,
                0, 0, 0, 1.2f, 0});
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

    }

    private void setZoomImageShader(Context context) {
        mBitmap = getBitmap(context, R.mipmap.ic_launcher);
        mBitmapScale = Bitmap.createScaledBitmap(mBitmap, mBitmap.getWidth() * 2, mBitmap.getHeight() * 2, true);
        BitmapShader shader = new BitmapShader(mBitmapScale, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mShapeDrawable = new ShapeDrawable(new OvalShape());
        mShapeDrawable.getPaint().setShader(shader);
        mShapeDrawable.setBounds(0, 0, 200, 200);
        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        handleFontLinearGradient();
    }

    private void handleFontLinearGradient() {
//        if (mViewWidth == 0) {
//            mPaint = getPaint();
//            mViewWidth = getMeasuredWidth();
//            if (mViewWidth > 0) {
//                String text = getText().toString();
//                int size = 0;
//                if (text.length() > 0) {
//                    size = mViewWidth / text.length() * 3;
//                } else {
//                    size = mViewWidth;
//                }
//                mLinearGradient = new LinearGradient(-size, 0f, 0f, 0f, new int[]{0x33ffffff, -0x1, 0x33ffffff}, new float[]{0f, 0.2f, 1f}, Shader.TileMode.CLAMP);
//                mPaint.setShader(mLinearGradient);
//                mGradientatrix = new Matrix();
//            }
//        }
    }

    private void setHeartShader(Context context) {
        mBitmap = getBitmap(context, R.mipmap.ic_launcher);
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        LinearGradient linearGradient = new LinearGradient(0, 0, 800, 800, mColors, null, Shader.TileMode.CLAMP);
        ComposeShader composeShader = new ComposeShader(linearGradient, shader, PorterDuff.Mode.MULTIPLY);
        mPaint.setShader(composeShader);
    }

    private void setComposeShader(Context context) {
        mBitmap = getBitmap(context, R.mipmap.ic_launcher);
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        LinearGradient linearGradient = new LinearGradient(0, 0, 800, 800, mColors, null, Shader.TileMode.REPEAT);
        ComposeShader composeShader = new ComposeShader(linearGradient, shader, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(composeShader);
    }

    private void setSweepGradient(Context context) {
        SweepGradient sweepGradient = new SweepGradient(500, 500, mColors, null);
        mPaint.setShader(sweepGradient);
    }

    private void setRadialGradient(Context context) {
        RadialGradient radialGradient = new RadialGradient(500, 500, 200, mColors, null, Shader.TileMode.REPEAT);
        mPaint.setShader(radialGradient);
    }

    private void setLinearGradient(Context context) {
        LinearGradient linearGradient = new LinearGradient(0, 0, 800f, 800f, mColors, null, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
    }

    private void setGradient(Context context) {
        mBitmap = getBitmap(context, R.mipmap.ic_launcher);
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        mPaint.setShader(shader);
        mPaint.setAntiAlias(true);

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

    private void setShadowLayer() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setAlpha(80);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint.setShadowLayer(50f, 100f, 100f, Color.GRAY);
        mPaint.setTextSize(50f);

    }

    int radius = 200;
    int offest = 40;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawShadowLayer(canvas);
//        drawGradient(canvas);
//        drawLinearGradient(canvas);
//        drawRadialGradient(canvas);
//        drawSweepGradient(canvas);
//        drawComposeShader(canvas);
//        drawHeartShader(canvas);
//        drawFontLinearGradient(canvas);
//        drawZoomImage(canvas);
//        drawFilterBitmap(canvas);
//        drawXfermode(canvas);

    }

    private void drawXfermode(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.save();
                canvas.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
                mPaint.setXfermode(null);
                int index = i * 4 + j;
            }
        }
    }

    private void drawFilterBitmap(Canvas canvas) {
        RectF rectF = new RectF(0, showHeight, mBitmap.getWidth() / 2, mBitmap.getHeight() / 4);
        showHeight += mBitmap.getHeight() / 4;
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
    }

    private void drawZoomImage(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, null);
        mShapeDrawable.draw(canvas);
    }

    private void drawFontLinearGradient(Canvas canvas) {
//        float measureWidth = mPaint.measureText(getText().toString());
//        mTranslate += delta;
//        /**
//         * 如果位置已经移动到了边界，那么文字就开始往回滚动
//         * 但是如果小于 1 那么又开始递增，执行另一个逻辑
//         */
//        if (mTranslate > measureWidth + 1 || mTranslate < 1) {
//            delta = -delta;
//        }
//        mGradientatrix.setTranslate(mTranslate, 0f);
//        mLinearGradient.setLocalMatrix(mGradientatrix);
//        //paint是textview的所以只需要不断色控制画笔的shader  然后利用矩阵控制位移即可
//        postInvalidateDelayed(30);
    }

    private void drawHeartShader(Canvas canvas) {
        canvas.drawRect(0, 0, 800, 800, mPaint);
    }

    private void drawComposeShader(Canvas canvas) {
        canvas.drawRect(0, 0, 800f, 1000f, mPaint);
    }

    private void drawSweepGradient(Canvas canvas) {
        canvas.drawCircle(500, 500, 200, mPaint);
    }

    private void drawRadialGradient(Canvas canvas) {
        canvas.drawCircle(500, 500, 200, mPaint);
    }

    private void drawLinearGradient(Canvas canvas) {
        canvas.drawRect(0f, 0f, 800f, 800f, mPaint);
    }

    private void drawGradient(Canvas canvas) {
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
    }

    private void drawShadowLayer(Canvas canvas) {
        Log.e("tag", "onDraw: ------height=" + getHeight() + ",width=" + getWidth());
        int startX = getWidth() / 2 - radius;
        int startY = getHeight() / 2;
        canvas.drawText("画一个圆", getWidth() / 2f, getHeight() / 2f, mPaint);
        canvas.drawText("画一个🐶", getWidth() / 4f, getHeight() / 4f, mPaint);
        canvas.drawText("画一个收视率", getWidth() / 4f, getHeight() / 4f, mPaint);
        canvas.drawCircle(startX + radius * 2 + offest, startY, radius, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        Log.e("tag", "onTouchEvent:----x= " + x + ",y=" + y);
        mMatrix.setTranslate(200 - x * 2, 200 - y * 2);
        mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);
        mShapeDrawable.setBounds(x - 200, y - 200, x + 200, y + 200);
        postInvalidate();
        return true;
    }

    private void initPaint() {
        //重制画笔paint
        mPaint.reset();
        //是否抗锯齿
        mPaint.setAntiAlias(true);
        //设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mPaint.setDither(true);
        //设置线性文本
        mPaint.setLinearText(true);
        //设置该项为ture,将有助于文本在LCD屏幕上的显示效果
        mPaint.setSubpixelText(true);
        //设置下划线
        mPaint.setUnderlineText(true);
        //设置带有删除线的效果
        mPaint.setStrikeThruText(true);
        //设置伪粗体文本，设置在小字体上效果会非常差
        mPaint.setFakeBoldText(true);
        //如果该项设置为true，则图像在动画进行中会滤掉对bitmap图片的优化操作
        //加快显示速度，本设置项依赖于dither和xfermode 的设置
        mPaint.setFilterBitmap(true);
        //设置画笔风格，空心或者实心FILL、STROKE、FILL_AND_STROKE
        mPaint.setStyle(Paint.Style.STROKE);
        //设置画笔颜色
        mPaint.setColor(Color.RED);
        //设置透明度
        mPaint.setAlpha(100);
        //设置RGB以及透明度
        mPaint.setARGB(100, 100, 100, 0);
        //设置，空心或者实心FILL、STROKE、FILL_AND_STROKE时，设置笔刷的粗细度
        mPaint.setStrokeWidth(10);
//        mPaint.setStrokeMiter();
        //设置空心画笔绘制的图形样式
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置绘制时各图形的结合方式，如平滑效果
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        //设置图像效果，使用Shader可以绘制各种渐变效果
//        mPaint.setShader()
        //设置颜色过滤器，可以在绘制颜色时实现不用颜色的变更效果
//        mPaint.setColorFilter()
        //设置图形重叠时的处理方式，如合并、取交集或者并集、经常用来制作橡皮的擦出效果
//        mPaint.setXfermode()
        //设置绘制路径的效果，如点画线等
//        mPaint.setPathEffect()
        //设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果、如滤化、立体等
//mPaint.setMaskFilter()
        //设置Typeface对象，即字体风格，包括粗体、斜体以及衬线体、非衬线体
//        mPaint.setTypeface()
        // 在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色
// 注意：在Android4.0以上默认开启硬件加速，有些图形的阴影无法显示。关闭View的硬件加速 view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        mPaint.setShadowLayer();
        //设置文本对齐方式
//        mPaint.setTextAlign();
        //设置字体大小
        mPaint.setTextSize(10);
        //设置文本缩放倍数、1.0为原始
        mPaint.setTextScaleX(0.5f);
        //设置斜体文字、SkewX为倾斜弧度
        mPaint.setTextSkewX(1);
    }
}
