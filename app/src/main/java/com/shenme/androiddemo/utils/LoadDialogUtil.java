package com.shenme.androiddemo.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.shenme.androiddemo.R;


public class LoadDialogUtil {


    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @return
     */
    public static Dialog createLoadingDialog(Context context) {
        ImageView view = new ImageView(context);
        view.setImageResource(R.drawable.background_loading_dialog);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        AnimationDrawable animation = (AnimationDrawable) view.getDrawable();
        Dialog loadingDialog = new Dialog(context, R.style.LoadingDialog);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(view);// 设置布局
        loadingDialog.getWindow().setLayout(Utils.dipDimensionInteger(context, 50f),
                Utils.dipDimensionInteger(context, 50f));
        animation.start();
        return loadingDialog;
    }

}
