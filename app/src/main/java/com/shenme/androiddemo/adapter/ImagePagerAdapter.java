package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.bean.product.Images;

import java.util.List;

/**
 * Created by CANC on 2016/11/29.
 */

public class ImagePagerAdapter extends PagerAdapter {
    private List<Images> list;
    private Context context;
    private boolean isInfiniteLoop;

    //获取真实的position
    public int getRealPosition(int position) {
        return isInfiniteLoop ? (position % list.size()) : position;
    }

    public void setData(List<Images> datas) {
        this.list = datas;
        this.notifyDataSetChanged();
    }


    public ImagePagerAdapter(Context context, List<Images> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null || list.size() == 0) {
            return 0;
        } else {
            return isInfiniteLoop ? Integer.MAX_VALUE : list.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(context);
        simpleDraweeView.setImageURI(list.get(getRealPosition(position)).url);
        simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(simpleDraweeView);
        return simpleDraweeView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((SimpleDraweeView) object);
    }

    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public void setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
    }
}
