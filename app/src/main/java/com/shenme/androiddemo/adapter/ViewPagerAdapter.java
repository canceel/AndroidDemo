package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.R;

import java.util.List;

/**
 * Created by CANC on 2016/10/17.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<String> list;
    private Context mContext;
    private ViewpagerOnitemClicklistener mViewpagerOnitemClicklistener;

    public ViewPagerAdapter(Context context, List<String> list, ViewpagerOnitemClicklistener viewpagerOnitemClicklistener) {
        this.mViewpagerOnitemClicklistener = viewpagerOnitemClicklistener;
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        // 无限滑动，设置返回为整数最大数
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // 初始化图片布局
        ImageView view = new ImageView(mContext);
        view.setBackgroundResource(R.mipmap.ic_launcher);
        //用SimpleDraweeView加载图片
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);
        simpleDraweeView.setImageURI(list.get(position % list.size()));
        container.addView(simpleDraweeView);
        //点击监听的回调，给View层使用，要把position回传
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewpagerOnitemClicklistener.setOnitemClicklistener(position % list.size());
            }
        });
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    //点击监听的接口
    public interface ViewpagerOnitemClicklistener {
        void setOnitemClicklistener(int position);
    }


}
