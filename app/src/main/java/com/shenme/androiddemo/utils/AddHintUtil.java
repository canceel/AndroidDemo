package com.shenme.androiddemo.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.GridViewWithHeaderAndFooter;
import com.shenme.androiddemo.R;


/**
 * Created by Administrator on 2016-6-7.
 */
public class AddHintUtil {
    private ImageView upImageView;
    private TextView hintTextView;
    private View footView;
    private int count = 0;


    public static final int IS_BOTTOM_STATE = 0;  //到底了
    public static final int NOT_BOTTOM_STATE = 1;//未到底，可上拉

    public void removeFootView(View listView) {
        if (count == 0) {
            return;
        }
        count--;
        if (listView instanceof ListView) {
            ((ListView) listView).removeFooterView(footView);
        } else if (listView instanceof GridViewWithHeaderAndFooter) {
            ((GridViewWithHeaderAndFooter) listView).removeFooterView(footView);
        } else return;
    }

    public void addFootView(Context context, View listView) {
        if (count == 1)
            return;
        count++;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (listView instanceof ListView) {
            footView = layoutInflater.inflate(R.layout.footlayout, null);
            ((ListView) listView).addFooterView(footView, null, false);
        } else if (listView instanceof GridViewWithHeaderAndFooter) {
            footView = layoutInflater.inflate(R.layout.footlayout1, null);
            ((GridViewWithHeaderAndFooter) listView).addFooterView(footView, null, false);
        } else return;
        hintTextView = (TextView) footView.findViewById(R.id.tv_hint);
        upImageView = (ImageView) footView.findViewById(R.id.iv_pull_up);
    }

    public void setVisibility() {
        if (footView != null) {
            footView.setVisibility(View.GONE);
        }
    }

    public void setState(int state, String text) {
        footView.setVisibility(View.VISIBLE);
        switch (state) {
            case IS_BOTTOM_STATE:
                if (text == null || text.isEmpty())
                    text = "已经到底儿了";
                hintTextView.setText(text);
                upImageView.setVisibility(View.GONE);
                break;
            case NOT_BOTTOM_STATE:
                if (text == null || text.isEmpty())
                    text = "上拉加载更多";
                hintTextView.setText(text);
                upImageView.setVisibility(View.VISIBLE);
                break;
            default:
                if (text == null || text.isEmpty())
                    text = "上拉加载更多";
                hintTextView.setText(text);
                upImageView.setVisibility(View.VISIBLE);
                break;
        }
    }

}
