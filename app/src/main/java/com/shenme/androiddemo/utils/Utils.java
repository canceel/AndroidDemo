package com.shenme.androiddemo.utils;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.widget.CircleBoard;

import java.util.List;

/**
 * Created by CANC on 2016/8/29.
 */
public class Utils {


    /**
     * 根据CircleBoard中的值设置颜色
     *
     * @param context
     * @param circleBoard
     * @param time        时间
     */
    public static void setCircle(Context context, CircleBoard circleBoard, int time) {
        int ringColor;
        circleBoard.setTextString(time + "");
        if (TextUtils.isEmpty(circleBoard.getmTextString())) {
            circleBoard.setTextString("XX");
            ringColor = context.getResources().getColor(R.color.gray_d7d7d7);
        } else {
            try {
                int text = Integer.parseInt(circleBoard.getmTextString());
                if (text == 0) {
                    ringColor = context.getResources().getColor(R.color.gray_d7d7d7);
                } else if (text > 0 && text <= 19) {
                    ringColor = context.getResources().getColor(R.color.red_fe3a39);
                } else if (text >= 20 && text <= 29) {
                    ringColor = context.getResources().getColor(R.color.yellow_ffc12d);
                } else if (text >= 30 && text <= 59) {
                    ringColor = context.getResources().getColor(R.color.green_2bc497);
                } else {
                    circleBoard.setTextString("完成");
                    ringColor = context.getResources().getColor(R.color.gray_d7d7d7);
                }
            } catch (Exception e) {
                circleBoard.setTextString("XX");
                ringColor = context.getResources().getColor(R.color.gray_d7d7d7);
            }

        }
        setColor(circleBoard, ringColor, Color.WHITE, ringColor, ringColor);

    }

    /**
     * 使用不同颜色
     *
     * @param circleBoard
     * @param ringColor          圆环颜色
     * @param circularPlateColor 圆板颜色
     * @param scaleColor         刻度颜色
     * @param textColor          文字颜色
     */
    public static void setColor(CircleBoard circleBoard, int ringColor, int circularPlateColor, int scaleColor, int textColor) {
        //设置圆圈背景
        circleBoard.setBackGround(ringColor);
        //设置内圆背景
        circleBoard.setmIBackGround(circularPlateColor);
        //设置刻度颜色
        circleBoard.setmScaleColor(scaleColor);
        //设置文字颜色
        circleBoard.setmTextColor(textColor);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dpToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int pxToDp(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue / scale) - 0.5f);
    }

    //设置Margint值

    //空
    public static LinearLayout.LayoutParams setMargint(Context context) {
        return setMargint(context, 0, 0, 0, 0);
    }

    //左边距
    public static LinearLayout.LayoutParams setMargint(Context context, int left) {
        return setMargint(context, left, 0, 0, 0);
    }

    //左上边距
    public static LinearLayout.LayoutParams setMargint(Context context, int left, int up) {
        return setMargint(context, left, up, 0, 0);
    }

    //左上右边距
    public static LinearLayout.LayoutParams setMargint(Context context, int left, int up, int right) {
        return setMargint(context, left, up, right, 0);
    }

    /**
     * @param left  左
     * @param up    上
     * @param right 右
     * @param down  下
     * @return
     */
    public static LinearLayout.LayoutParams setMargint(Context context, int left, int up, int right, int down) {
        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(dpToPx(context, left), dpToPx(context, up), dpToPx(context, right), dpToPx(context, down));
        return layoutParams;
    }

    /**
     * 设置Caramer打开闪光灯
     *
     * @param mCamera
     */
    public static void returnLightOpen(Camera mCamera) {
        if (mCamera == null) {
            return;
        }
        Parameters parameters = mCamera.getParameters();
        if (parameters == null) {
            return;
        }
        List<String> flashModes = parameters.getSupportedFlashModes();
        if (flashModes == null) {
            return;
        }
        if (!parameters.FLASH_MODE_TORCH.equals(flashModes)) {
            if (flashModes.contains(parameters.FLASH_MODE_TORCH)) {
                parameters.setFlashMode(parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(parameters);
            }
        }

    }

    /**
     * 设置Caramer关闭闪关灯
     *
     * @param mCamera
     */
    public static void returnLightOff(Camera mCamera) {
        if (mCamera == null) {
            return;
        }
        Parameters parameters = mCamera.getParameters();
        if (parameters == null) {
            return;
        }
        List<String> flashModes = parameters.getSupportedFlashModes();
        if (flashModes == null) {
            return;
        }
        if (!parameters.FLASH_MODE_OFF.equals(flashModes)) {
            if (flashModes.contains(parameters.FLASH_MODE_OFF)) {
                parameters.setFlashMode(parameters.FLASH_MODE_OFF);
                mCamera.setParameters(parameters);
            }
        }
    }


}
