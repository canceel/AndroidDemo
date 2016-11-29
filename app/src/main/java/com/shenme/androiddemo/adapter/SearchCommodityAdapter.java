package com.shenme.androiddemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.general.SearchProductActivity;
import com.shenme.androiddemo.utils.MyAnimationUtils;
import com.shenme.androiddemo.utils.Utils;

import java.util.List;

/**
 * Created by king on 2015/7/21.
 */
public class SearchCommodityAdapter extends CommonAdapter<SearchProductActivity.TwoProducts> {
    private AddProductListener listener;
    private ImageView shoppingcart;
    private int type;

    public SearchCommodityAdapter(Context context,
                                  List<SearchProductActivity.TwoProducts> datas,
                                  int viewStyle,
                                  AddProductListener listener) {
        super(context, datas);
        shoppingcart = MyAnimationUtils.getShoppingCart(context);
        this.listener = listener;
        updateViewStyle(viewStyle);
    }

    @Override
    public void convert(final ViewHolder holder, SearchProductActivity.TwoProducts product) {
        LinearLayout linearlayout = holder.getView(R.id.linearlayout);
        SimpleDraweeView iv = holder.getView(R.id.iv_goods);
        TextView tv_name = holder.getView(R.id.tv_name);
        TextView tv_summary = holder.getView(R.id.tv_summary);
        TextView tv_current_price = holder.getView(R.id.tv_current_price);
        TextView tv_original_price = holder.getView(R.id.tv_original_price);
        Button btn_add = holder.getView(R.id.btn_add);
        RelativeLayout stockLevel = holder.getView(R.id.stock_level);
        tv_name.setText(product.list.get(0).name);
        tv_summary.setText(product.list.get(0).summary);

        // 是否有促销价
        isEmptyPromotionPrice(tv_current_price, tv_original_price, product.list.get(0).productFixedUnitPrice, product.list.get(0).price);

        iv.setImageURI(product.list.get(0).thumbnail);

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = ((type == SearchProductActivity.VIEW_STYLE_LIST) ? holder.position : holder.position * 2);
                listener.linkToProduct(position);
            }
        });
        //进入商品详情
        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = ((type == SearchProductActivity.VIEW_STYLE_LIST) ? holder.position : holder.position * 2);
                listener.linkToProduct(position);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = ((type == SearchProductActivity.VIEW_STYLE_LIST) ? holder.position : holder.position * 2);
                listener.addToCart(position, (Activity) context, shoppingcart, (ImageView) holder.getView(R.id.iv_goods));
            }
        });
        if (type == SearchProductActivity.VIEW_STYLE_GRID) {
            LinearLayout linearlayout1 = holder.getView(R.id.linearlayout1);
            linearlayout1.setVisibility(View.VISIBLE);
            if (product.list.size() == 2) {
                SimpleDraweeView iv1 = holder.getView(R.id.iv_goods1);
                TextView tv_name1 = holder.getView(R.id.tv_name1);
                TextView tv_summary1 = holder.getView(R.id.tv_summary1);
                TextView tv_current_price1 = holder.getView(R.id.tv_current_price1);
                TextView tv_original_price1 = holder.getView(R.id.tv_original_price1);
                Button btn_add1 = holder.getView(R.id.btn_add1);
                RelativeLayout stockLevel1 = holder.getView(R.id.stock_level1);
                tv_name1.setText(product.list.get(1).name);
                tv_summary1.setText(product.list.get(1).summary);

                // 是否有促销价
                isEmptyPromotionPrice(tv_current_price1, tv_original_price1, product.list.get(1).productFixedUnitPrice, product.list.get(1).price);

//                //是否有货
//                if ("1".equals(product.list.get(1).stockLevels)) {
//                    stockLevel1.setVisibility(View.GONE);
//                } else {
//                    stockLevel1.setVisibility(View.VISIBLE);
//                }
                //设置大小
                if (type == SearchProductActivity.VIEW_STYLE_GRID) {
                    int screendWidth = Utils.getScreenWidth(context);
                    iv.getLayoutParams().width = (screendWidth / 2) - 1;
                    iv.getLayoutParams().height = (screendWidth / 2) - 1;
                    stockLevel.getLayoutParams().width = (screendWidth / 2) - 1;
                    stockLevel.getLayoutParams().height = (screendWidth / 2) - 1;

                    iv1.getLayoutParams().width = (screendWidth / 2) - 1;
                    iv1.getLayoutParams().height = (screendWidth / 2) - 1;
                    stockLevel1.getLayoutParams().width = (screendWidth / 2) - 1;
                    stockLevel1.getLayoutParams().height = (screendWidth / 2) - 1;
                }

                iv1.setImageURI(product.list.get(1).thumbnail);
                linearlayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.linkToProduct((holder.position * 2 + 1));
                    }
                });
                tv_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.linkToProduct((holder.position * 2 + 1));
                    }
                });
                btn_add1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.addToCart((holder.position * 2 + 1), (Activity) context, shoppingcart, (ImageView) holder.getView(R.id.iv_goods1));
                    }
                });

            } else {
                linearlayout1.setVisibility(View.INVISIBLE);
            }
        }
    }


    public interface AddProductListener {
        void addToCart(int position, Activity context, ImageView shoppingcart, ImageView product);

        void linkToProduct(int position);

    }

    private void updateViewStyle(int viewStyle) {
        type = viewStyle;
        switch (viewStyle) {
            case SearchProductActivity.VIEW_STYLE_LIST:
                this.layoutId = R.layout.listview_item_search_list;
                break;
            case SearchProductActivity.VIEW_STYLE_GRID:
                this.layoutId = R.layout.listview_item_search_grid;
                break;
            default:
                this.layoutId = R.layout.listview_item_search_grid;
                break;
        }
    }

    /**
     * 是否有促销价
     *
     * @param textView1
     * @param textView2
     * @param productFixedUnitPrice 促销价
     * @param price                 原价
     */
    private static void isEmptyPromotionPrice(TextView textView1, TextView textView2, String productFixedUnitPrice, String price) {
        if (TextUtils.isEmpty(productFixedUnitPrice)) {
            textView1.setText(Utils.PriceFormat(price));
            textView2.setText("");
        } else {
            textView1.setText(Utils.PriceFormat(productFixedUnitPrice));
            textView2.setText(Utils.PriceFormat(price));
        }
        textView2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

}
