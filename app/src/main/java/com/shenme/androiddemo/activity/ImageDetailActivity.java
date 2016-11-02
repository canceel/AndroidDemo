package com.shenme.androiddemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shenme.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import immortalz.me.library.TransitionsHeleper;
import immortalz.me.library.bean.InfoBean;
import immortalz.me.library.method.InflateShowMethod;

/**
 * Created by CANC on 2016/11/1.
 */

public class ImageDetailActivity extends Activity {
    @BindView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail);
        ButterKnife.bind(this);
        TransitionsHeleper.getInstance()
                .setShowMethod(new InflateShowMethod(this, R.layout.image_detail_) {
                    @Override
                    public void loadCopyView(InfoBean bean, ImageView copyView) {
                        Glide.with(ImageDetailActivity.this)
                                .load(bean.getImgUrl())
                                .fitCenter()
                                .into(copyView);

                    }

                    @Override
                    public void loadTargetView(InfoBean bean, ImageView targetView) {
                        Glide.with(ImageDetailActivity.this)
                                .load(bean.getImgUrl())
                                .fitCenter()
                                .into((ImageView) targetView);
                    }
                })
                .show(this, imageView);
    }
}
