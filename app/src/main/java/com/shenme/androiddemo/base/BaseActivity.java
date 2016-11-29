package com.shenme.androiddemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.shenme.androiddemo.activity.MainActivity;
import com.shenme.androiddemo.widget.MyEmptyViewHelper;
import com.umeng.message.PushAgent;

/**
 * Created by CANC on 2016/11/1.
 */

public class BaseActivity extends Activity {
    public Context mContext;
    public String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        TAG = mContext.getClass().getSimpleName();
        PushAgent.getInstance(mContext).onAppStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    /**
     * 返回事件
     *
     * @param v 返回按钮View
     */
    public void back(View v) {
        onBackPressed();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    public void retry(int tab) {
        if (tab == MyEmptyViewHelper.RETRY_TYPE_GOTO_INDEX) {
            Intent it = new Intent(this, MainActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
            onBackPressed();
        }
    }

    /**
     * 设置页面的空白页
     *
     * @param originalView 加载空白页的父View
     * @param emptyView    空白页View
     * @param type         空白页类型
     */
    public View setEmptyView(View originalView, View emptyView, int type) {
        return MyEmptyViewHelper.setEmptyView(originalView, emptyView, type);
    }

    /**
     * 移除空白页
     *
     * @param originalView 加载空白页的父View
     * @param emptyView    空白页View
     */
    public void removeEmptyView(View originalView, View emptyView) {
        MyEmptyViewHelper.removeEmptyView(originalView, emptyView);
    }

}
