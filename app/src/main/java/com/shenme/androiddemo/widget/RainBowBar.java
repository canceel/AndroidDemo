package com.shenme.androiddemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.utils.Utils;

/**
 * 自定义VIEW实现进度条
 * Created by CANC on 2016/10/12.
 */

public class RainBowBar extends View {

    //进度条颜色
    private int barColor = Color.BLUE;
    //宽度
    private int hSpace = Utils.dpToPx(getContext(), 10);
    //高度
    private int vSpace = Utils.dpToPx(getContext(), 4);
    //
    private int space = Utils.dpToPx(getContext(), 10);
    private float startX = 0;
    private int startY = 5;
    private int stopY = 5;
    private float delta = 10f;
    private Paint mPaint;
    //draw be invoke numbers.

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(barColor);
        mPaint.setStrokeWidth(vSpace);
    }

    public RainBowBar(Context context) {
        super(context);
    }

    public RainBowBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        initPaint();
    }

    public RainBowBar(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.RainBowBar, 0, 0);
        hSpace = typedArray.getDimensionPixelSize(R.styleable.RainBowBar_rainbow_hspace, hSpace);
        vSpace = typedArray.getDimensionPixelSize(R.styleable.RainBowBar_rainbow_vspace, vSpace);
        barColor = typedArray.getColor(R.styleable.RainBowBar_rainbow_color, barColor);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
    }

    public static int getDefaultSize(int size, int measureSize) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSize);
        int specSize = MeasureSpec.getSize(measureSize);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取屏幕宽度
        float sw = this.getMeasuredWidth();
        if (startX >= sw + (hSpace + space) - (sw % (hSpace + space))) {
            startX = 0;
        } else {
            startX += delta;
        }
        float start = startX;
        //画后
        while (start < sw) {
            canvas.drawLine(start, startY, start + hSpace, stopY, mPaint);
            start += (hSpace + space);
        }
        start = startX - hSpace - space;
        //画前
        while (start > -hSpace) {
            canvas.drawLine(start, startY, start + hSpace, stopY, mPaint);
            start -= (hSpace + space);
        }
        postInvalidateDelayed(1);
//        invalidate();


    }
}
