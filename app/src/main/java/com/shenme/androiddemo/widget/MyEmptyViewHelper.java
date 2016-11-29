package com.shenme.androiddemo.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.base.BaseActivity;


/**
 * Created by king on 2015/9/3.
 */
public class MyEmptyViewHelper {

    public static final int TYPE_NETWORK_ERROR = -1;
    public static final int TYPE_ORDER_EMPTY = 0;
    public static final int TYPE_COLLECTION_EMPTY = 1;
    public static final int TYPE_EVALUATION_EMPTY = 2;
    public static final int TYPE_CART_EMPTY = 3;
    public static final int TYPE_INDEX_EMPTY = 4;
    public static final int TYPE_COUPONS_EMPTY = 5;
    public static final int TYPE_DEFAULT_EMPTY = 6;
    public static final int TYPE_ADDRESS_EMPTY = 7;
    public static final int TYPE_PRODUCT_EMPTY = 8;
    public static final int TYPE_PRODUCT_EVALUATION_EMPTY = 9;
    public static final int TYPE_FILTER_EMPTY = 10;
    public static final int TYPE_ENDECAPRODUCTRECORDS_EMPTY = 11;
    public static final int TYPE_LEFTMENUITEMS_EMPTY = 12;
    public static final int TYPE_CHOOSE_STORE_EMPTY = 13;
    public static final int TYP_RETURN_ORDER_EMPTY = 14;
    public static final int TYPE_INDEX_EMPTY_FAILURE = 15;
    public static final int TYPE_SORT_EMPTY = 16;


    public static final int RETRY_TYPE_NETWORK_ERROR = 0;
    public static final int RETRY_TYPE_GOTO_INDEX = 1;
    public static final int RETRY_TYPE_GOTO_HOME = 2;

    private static int type;

    /**
     * 设置，并返回，空页面View
     *
     * @param view
     * @param type
     * @return
     */
    public static View setEmptyView(View view, View emptyView, int type) {
        final Context context = view.getContext();
        if (emptyView == null || MyEmptyViewHelper.type != type) {
            if (MyEmptyViewHelper.type != type) {
                if (emptyView != null && emptyView.getParent() != null) {
                    ((ViewGroup) emptyView.getParent()).removeView(emptyView);
                }
            }
            MyEmptyViewHelper.type = type;
            emptyView = View.inflate(context, R.layout.layout_empty, null);
            emptyView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ImageView icon = (ImageView) emptyView.findViewById(R.id.icon);
            TextView text = (TextView) emptyView.findViewById(R.id.text);
            Button retry = (Button) emptyView.findViewById(R.id.retry);
            if (emptyView.getParent() != null) {
                ((ViewGroup) emptyView.getParent()).removeView(emptyView);
            }

            switch (type) {
//                case TYPE_ORDER_EMPTY:
//                    icon.setImageResource(R.mipmap.order_empty);
//                    text.setText("您还没有相关订单");
//                    gotoIndexActivity(retry, context);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_ADDRESS_EMPTY:
//                    icon.setImageResource(R.mipmap.address_empty);
//                    text.setText("暂无收货地址，请新增");
//                    retry.setVisibility(View.GONE);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_COLLECTION_EMPTY:
//                    icon.setImageResource(R.mipmap.collection_empty);
//                    text.setText("您还没有收藏哦～");
//                    gotoIndexActivity(retry, context);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_EVALUATION_EMPTY:
//                    icon.setImageResource(R.mipmap.pj_empty);
//                    text.setText("您还没有评价哦");
//                    gotoIndexActivity(retry, context);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_PRODUCT_EVALUATION_EMPTY:
//                    icon.setImageResource(R.mipmap.pj_empty);
//                    text.setText("所有商品已评价");
//                    retry.setVisibility(View.GONE);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_CART_EMPTY:
//                    icon.setImageResource(R.mipmap.icon_cart_empty);
//                    text.setText("您的购物车是空的");
//                    gotoIndexActivity(retry, context);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_INDEX_EMPTY:
//                    icon.setImageResource(R.mipmap.icon_default_shopping_list);
//                    text.setText("不好意思，本店还未开张，请稍后再来～");
//                    retry.setVisibility(View.GONE);
//                    retry.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ((BaseActivity) context).retry(RETRY_TYPE_NETWORK_ERROR);
//                        }
//                    });
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_COUPONS_EMPTY:
//                    icon.setImageResource(R.mipmap.coupons_empty);
//                    text.setText("您还没有优惠券哦～");
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_PRODUCT_EMPTY:
//                case TYPE_ENDECAPRODUCTRECORDS_EMPTY:
////                    emptyView.setBackgroundResource(R.color.background);
//                    icon.setImageResource(R.mipmap.product_empty);
//                    text.setText("没有找到相关商品呢～");
//                    retry.setVisibility(View.GONE);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_LEFTMENUITEMS_EMPTY:
////                    icon.setImageResource(R.mipmap.leftmenuitems_empty);
//                    text.setText("暂无商品分类");
//                    retry.setVisibility(View.GONE);
//                    ((LinearLayout) emptyView).setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_FILTER_EMPTY:
//                    icon.setImageResource(R.mipmap.filter_empty);
//                    LinearLayout.LayoutParams ll1 = (LinearLayout.LayoutParams) icon
//                            .getLayoutParams();
//                    ll1.topMargin = 10;
//                    icon.setLayoutParams(ll1);
//                    text.setText("暂无筛选条件");
//                    retry.setVisibility(View.GONE);
//                    ViewGroup group = ((ViewGroup) view.getParent());
//                    group.removeView(view);
//                    group.addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_NETWORK_ERROR:
//                    ViewGroup.LayoutParams lp = view.getLayoutParams();
//                    icon.setVisibility(View.GONE);
//                    text.setText("获得网络数据失败");
//                    retry.setVisibility(View.VISIBLE);
//                    retry.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ((BaseActivity) context).retry(RETRY_TYPE_NETWORK_ERROR);
//                        }
//                    });
//                    ((ViewGroup) view.getParent()).addView(emptyView, lp);
//                    break;
//                case TYPE_CHOOSE_STORE_EMPTY:
//                    icon.setVisibility(View.GONE);
//                    text.setText("暂无数据,请更换地址");
//                    retry.setVisibility(View.GONE);
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
//                case TYPE_DEFAULT_EMPTY:
//                    icon.setImageResource(R.mipmap.icon_default_search_result);
//                    text.setText("暂无数据哦~");
//                    retry.setVisibility(View.GONE);
//                    ((ViewGroup) view.getParent()).addView(emptyView);
//                    break;
//                case TYPE_INDEX_EMPTY_FAILURE:
//                    icon.setImageResource(R.mipmap.icon_default_shopping_list);
//                    text.setText("网络请求失败，请重试");
//                    retry.setVisibility(View.VISIBLE);
//                    retry.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            ((BaseActivity) context).retry(RETRY_TYPE_NETWORK_ERROR);
//                        }
//                    });
//                    ((ViewGroup) view.getParent()).addView(emptyView, view.getLayoutParams());
//                    break;
            }
        } else {
            if (emptyView.getParent() != null) {
                ((ViewGroup) emptyView.getParent()).removeView(emptyView);
            }
            ((ViewGroup) view.getParent()).addView(emptyView);
        }
        if (type != TYPE_FILTER_EMPTY) {
            view.setVisibility(View.GONE);
        }

        return emptyView;
    }

    /**
     * 移除空View
     *
     * @param view
     * @param emptyView
     */
    public static void removeEmptyView(View view, View emptyView) {
        ViewGroup group = null;
        if (emptyView != null) {
            group = ((ViewGroup) view.getParent());
            group.removeView(emptyView);
            if (group instanceof ScrollView) {
                group.addView(view, emptyView.getLayoutParams());
            }
        }
        if (group != null && !(group instanceof ScrollView)) {
            view.setVisibility(View.VISIBLE);
        } else if (group == null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    private static void gotoIndexActivity(TextView retry, final Context context) {
        retry.setVisibility(View.VISIBLE);
        retry.setText("去逛逛吧～");
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) context).retry(RETRY_TYPE_GOTO_INDEX);
            }
        });
    }


}
