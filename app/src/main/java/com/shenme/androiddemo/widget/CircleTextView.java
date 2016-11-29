package com.shenme.androiddemo.widget;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.TextView;

import com.shenme.androiddemo.R;


/**
 * Created by allipper on 2015/9/3.
 */
public class CircleTextView extends TextView {

    private Paint mBgPaint = new Paint();

    PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint
            .FILTER_BITMAP_FLAG);

    public CircleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBgPaint.setColor(context.getResources().getColor(R.color.text_fea423));
        mBgPaint.setAntiAlias(true);
    }

    public CircleTextView(Context context) {
        super(context);
        mBgPaint.setColor(context.getResources().getColor(R.color.text_fea423));
        mBgPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int max = Math.max(measuredWidth, measuredHeight);
        setMeasuredDimension(max, max);
    }

    @Override
    public void setBackgroundColor(int color) {
        mBgPaint.setColor(color);
    }

    /**
     * 设置通知个数显示
     *
     * @param text
     */
    public void setNotifyText(int text) {
        if (text > 99) {
            String string = 99 + "+";
            setText(string);
            return;
        } else if (text < 1) {
            setText("");
        } else {
            setText(text + "");
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(pfd);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.max(getWidth(), getHeight()) / 2,
                mBgPaint);
        super.draw(canvas);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.setDrawFilter(pfd);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.max(getWidth(), getHeight()) / 2,
                mBgPaint);
        super.onDraw(canvas);
    }
}