package com.shenme.androiddemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.utils.SizeUtil;

/**
 * 自定义VIEW实现圆盘提示面板
 * Created by CANC on 2016-9-20.
 */
public class CircleBoard extends View {
    private float mRadius; //外圆半径
    private float mIRadius; //内圆半径
    private float mPadding; //边距
    private int mBackGround = Color.GREEN;//外圆背景色
    private int mIBackGround = Color.WHITE;//内圆背景色
    private int mScaleColor = Color.GREEN; //刻度颜色
    private int mTextColor = Color.GREEN; //文字颜色
    private int lineWidth = 15;//刻度长度
    private Paint mPaint; //画笔
    private String mTextString = "0分";//中心文字

    //设置外圆背景颜色
    public void setBackGround(int mBackGround) {
        this.mBackGround = mBackGround;
        this.invalidate();
    }

    //设置内圆背景颜色
    public void setmIBackGround(int mIBackGround) {
        this.mIBackGround = mIBackGround;
        this.invalidate();
    }

    //设置刻度颜色
    public void setmScaleColor(int mScaleColor) {
        this.mScaleColor = mScaleColor;
        this.invalidate();
    }

    //设置文字颜色
    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        this.invalidate();
    }

    //设置中心文字
    public void setTextString(String mTextString) {
        this.mTextString = mTextString;
        this.invalidate();
    }

    //获取文字
    public String getmTextString() {
        return mTextString;
    }

    public CircleBoard(Context context) {
        this(context, null);
    }

    public CircleBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        obtainStyledAttrs(attrs); //获取自定义的属性
        init(); //初始化画笔
    }

    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray array = null;
        try {
            array = getContext().obtainStyledAttributes(attrs, R.styleable.WatchBoard);
            mPadding = array.getDimension(R.styleable.WatchBoard_wb_padding, DptoPx(10));
        } catch (Exception e) {
            //一旦出现错误全部使用默认值
            mPadding = DptoPx(10);
        } finally {
            if (array != null) {
                array.recycle();
            }
        }
    }

    //Dp转px
    private float DptoPx(int value) {
        return SizeUtil.Dp2Px(getContext(), value);
    }

    //画笔初始化
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 1000; //设定一个最小值


        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED || heightMeasureSpec == MeasureSpec.AT_MOST || heightMeasureSpec == MeasureSpec.UNSPECIFIED) {
            try {
                throw new NoDetermineSizeException("宽度高度至少有一个确定的值,不能同时为wrap_content");
            } catch (NoDetermineSizeException e) {
                e.printStackTrace();
            }
        } else { //至少有一个为确定值,要获取其中的最小值
            if (widthMode == MeasureSpec.EXACTLY) {
                width = Math.min(widthSize, width);
            }
            if (heightMode == MeasureSpec.EXACTLY) {
                width = Math.min(heightSize, width);
            }
        }
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadius = ((Math.min(w, h) - getPaddingLeft() - getPaddingRight()) / 2) - 2;
        mIRadius = mRadius - DptoPx(5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //绘制外圆背景
        paintCircle(canvas, mBackGround);
        //绘制内部圆圈
        paintICircle(canvas, mIBackGround);
        //绘制刻度
        paintScale(canvas, mScaleColor);
        //绘制中心文字
        paintText(canvas, mTextColor);
        //重置
        canvas.restore();
        //刷新
        postInvalidateDelayed(1000);
    }

    //绘制外圆背景
    public void paintCircle(Canvas canvas, int color) {
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(0, 0, mRadius, mPaint);
    }

    //绘制内部圆圈
    private void paintICircle(Canvas canvas, int color) {
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(0, 0, mIRadius, mPaint);
    }


    //绘制中心文字
    private void paintText(Canvas canvas, int color) {
        canvas.save();
        //绘制文字
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(DptoPx(24));
        mPaint.setColor(color);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(mTextString, 0, 20, mPaint);
    }

    //绘制刻度
    private void paintScale(Canvas canvas, int color) {
        mPaint.setStrokeWidth(DptoPx(1));
        for (int i = 0; i < 60; i++) {
            mPaint.setColor(color);
            mPaint.setStrokeWidth(DptoPx(1));
            canvas.drawLine(0, -mRadius + mPadding, 0, -mRadius + mPadding + lineWidth, mPaint);
            canvas.rotate(6);
        }
    }

    class NoDetermineSizeException extends Exception {
        public NoDetermineSizeException(String message) {
            super(message);
        }
    }
}
