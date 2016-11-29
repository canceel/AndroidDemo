package com.shenme.androiddemo.widget;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.shenme.androiddemo.R;


/**
 * 自定义一个具有清除功能的editText
 *
 * @author xiejinxiong
 */
public class MyClearEditText extends EditText implements TextWatcher {
    /**
     * 储存清除的图片
     */
    private Drawable draw;

    public MyClearEditText(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public MyClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
        // TODO Auto-generated constructor stub
    }

    public MyClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initClearDrawable();
        this.addTextChangedListener(this);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        // TODO Auto-generated method stub
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        // 判断焦点失去和得到时的操作
        if (focused && !this.getText().toString().equals("")) {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, draw, null);
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    /**
     * 初始化清除的图片
     */
    private void initClearDrawable() {
        draw = getCompoundDrawables()[2];

        // 判断清除的图片是否为空
        if (draw == null) {
            draw = getResources().getDrawable(R.mipmap.icon_delete_text);
        }

        // 将输入框默认设置为没有清除的按钮
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }

    public void onTextChanged(CharSequence text, int start, int lengthBefore,
                              int lengthAfter) {
        // 判断输入框中是否有内容
        if (text.length() > 0) {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, draw, null);
        } else {
            this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 判断触碰是否结束
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // 判断所触碰的位置是否为清除的按钮
            if (event.getX() > (getWidth() - getTotalPaddingRight())
                    && event.getX() < (getWidth() - getPaddingRight())) {
                // 将editText里面的内容清除
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

}