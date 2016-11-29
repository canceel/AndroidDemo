package com.shenme.androiddemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shenme.androiddemo.widget.CircleImageView;


/**
 * Created by Administrator on 2016-5-27.
 */
public class MyAnimationUtils {
    public static boolean change = true;
    public static long currentTime = 0;

    public static ImageView getShoppingCart(Context context) {
        if (context instanceof GetShoppingCartInterface)
            return ((GetShoppingCartInterface) context).getShoppingCart();

        else {
        }
        return null;

    }

    /**
     * @param activity
     * @param shoppingcart
     * @param product
     * @param productCode
     * @param groupCodes
     * @param quality
     * @param nobee
     */
    public static void addToShoppingCart(Activity activity, ImageView shoppingcart, ImageView product, String productCode,
                                         String groupCodes,
                                         int quality,
                                         boolean nobee) {
        if (System.currentTimeMillis() - currentTime < 600) {
            return;
        }
        currentTime = System.currentTimeMillis();
        if (LinkToUtils.checkAndLoginActivity(activity)) {
            return;
        }
        LinkToUtils.addProductToCart(activity, productCode, String.valueOf(quality));
        int location[] = new int[2];
        product.getLocationInWindow(location);
        location[0] = location[0] + product.getWidth() / 2 - Utils.dipDimensionInteger(activity, 30);
        location[1] = location[1] + product.getHeight() / 2 - Utils.dipDimensionInteger(activity, 30);
        CircleImageView buyImg = new CircleImageView(activity);// 动画图片
        Drawable bitmap = product.getDrawable();
        buyImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        buyImg.setImageDrawable(bitmap);// 设置buyImg的图片

        setAnim(activity, buyImg, location, shoppingcart);// 开始执行动画
    }

    public static void simpleAddToShoppingCart(Context context,
                                               ImageView shoppingcart,
                                               ImageView productImage,
                                               View productView) {
        if (System.currentTimeMillis() - currentTime < 600) {
            return;
        }
        currentTime = System.currentTimeMillis();
        if (LinkToUtils.checkAndLoginActivity(context)) {
            return;
        }
        int location[] = new int[2];
        productView.getLocationInWindow(location);
        location[0] = location[0] + productView.getWidth() / 2 - Utils.dipDimensionInteger(context, 30);
        location[1] = location[1] + productView.getHeight() / 2 - Utils.dipDimensionInteger(context, 30);
        CircleImageView buyImg = new CircleImageView(context);// 动画图片
        Drawable bitmap = productImage.getDrawable();
//        Drawable bitmap = context.getResources().getDrawable(R.mipmap.icon_product_shopping_cart);
        buyImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        buyImg.setImageDrawable(bitmap);// 设置buyImg的图片
        setAnim((Activity) context, buyImg, location, shoppingcart);// 开始执行动画
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    public static ViewGroup createAnimLayout(Activity activity) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        int max = Integer.MAX_VALUE;
        animLayout.setId(max);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private static View addViewToAnimLayout(final View view,
                                            int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    private static void setAnim(Activity activity, final CircleImageView buyImg, int[] start_location, final View btnShoppingCart) {
        ViewGroup anim_mask_layout = null;
        anim_mask_layout = createAnimLayout(activity);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(Utils.dipDimensionInteger(activity, 60), Utils.dipDimensionInteger(activity, 60));
        anim_mask_layout.addView(buyImg, ll);//把动画添加到动画层
        final View view = addViewToAnimLayout(buyImg,
                start_location);
        int[] end_location = new int[2];// 这是用来存储动画结束位置的X、Y坐标
        btnShoppingCart.getLocationInWindow(end_location);// 购物车

        // 计算位移
        int endX = end_location[0] - start_location[0] + btnShoppingCart.getWidth() / 2;// 动画位移的X坐标
        int endY = end_location[1] - start_location[1] + btnShoppingCart.getHeight() / 2;// 动画位移的y坐标
        int weiyi = (int) Math.sqrt(Math.pow(endX, 2) + Math.pow(endY, 2));
        int width = ((WindowManager) activity
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        int time = (int) (weiyi * 1.0f / width * 600);
        if (time < 600)
            time = 600;
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(false);
        translateAnimationX.setDuration(time);
        TranslateAnimation translateAnimationY = new TranslateAnimation(0,
                0, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator(1.5f));
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationY.setFillAfter(false);
        translateAnimationY.setDuration(time);
        ScaleAnimation scaleAnimation;
        scaleAnimation = new ScaleAnimation(1.0f,
                0.1f, 1.0f, 0.1f);
        scaleAnimation.setInterpolator(new LinearInterpolator());
        scaleAnimation.setRepeatCount(0);// 动画重复执行的次数
        scaleAnimation.setFillAfter(false);
        scaleAnimation.setDuration(time);
        //移动到购物车
        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(scaleAnimation);
        set.addAnimation(translateAnimationX);
        set.addAnimation(translateAnimationY);
        set.setDuration(time);// 动画的执行时间
        view.startAnimation(set);


        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                ((ViewGroup) buyImg.getParent()).removeView(buyImg);
                if (change) {
                    //放大购物车
                    ScaleAnimation enlargeShoppingcart = new ScaleAnimation(1f,
                            1.25f, 1f, 1.25f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    enlargeShoppingcart.setInterpolator(new LinearInterpolator());
                    enlargeShoppingcart.setRepeatCount(0);// 动画重复执行的次数
                    enlargeShoppingcart.setFillAfter(true);
                    enlargeShoppingcart.setDuration(200);
                    //缩小购物车
                    ScaleAnimation narrowShoppingcart = new ScaleAnimation(1f,
                            0.8f, 1f, 0.8f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    narrowShoppingcart.setInterpolator(new LinearInterpolator());
                    narrowShoppingcart.setRepeatCount(0);// 动画重复执行的次数
                    narrowShoppingcart.setFillAfter(true);
                    narrowShoppingcart.setDuration(200);
                    narrowShoppingcart.setStartOffset(200);
                    //缩放购物车
                    AnimationSet animationSet = new AnimationSet(true);
                    animationSet.setFillAfter(true);
                    animationSet.addAnimation(enlargeShoppingcart);
                    animationSet.addAnimation(narrowShoppingcart);
                    animationSet.setDuration(400);
                    btnShoppingCart.startAnimation(animationSet);

                    animationSet.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            change = false;

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            change = true;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

            }
        });

    }


    public interface GetShoppingCartInterface {
        ImageView getShoppingCart();
    }


}
