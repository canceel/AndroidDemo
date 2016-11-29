package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.shoppingcart.Entry;
import com.shenme.androiddemo.utils.Utils;

import java.util.List;

/**
 * Created by CANC on 2016/11/9.
 * 结算界面商品信息
 */
public class ProductAdapter extends CommonAdapter<Entry> {

    public final static int TYPE_NORMAL = 0;

    protected int layoutId;

    public ProductAdapter(Context context, List<Entry> datas) {
        super(context, datas);
        this.layoutId = R.layout.activity_mine_order_detail_product_item;
    }

    @Override
    public void convert(ViewHolder holder, Entry entry) {

        SimpleDraweeView simpleDraweeView = holder.getView(R.id.product_icon_iv);
        TextView nameTv = holder.getView(R.id.name_tv);
        TextView goodsOrignalPriceTv = holder.getView(R.id.goods_orignal_price_tv);
        TextView productStandard = holder.getView(R.id.product_standard);
        TextView goodsNoTv = holder.getView(R.id.goods_no_tv);
        //设置第一个图片
        simpleDraweeView.setImageURI(entry.getImageUrl());
        //设置商品名称
        nameTv.setText(entry.getProductName());

        if (!TextUtils.isEmpty(entry.getActPrice())) {
            goodsOrignalPriceTv.setVisibility(View.VISIBLE);
            goodsOrignalPriceTv.setText(Utils.PriceFormat(entry.getActPrice()));
        } else {
            goodsOrignalPriceTv.setVisibility(View.GONE);
        }
        //设置商品数量
        goodsNoTv.setText(entry.getQuantity() + "");
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Entry getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder holder = ViewHolder.get(context, convertView, parent, layoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public void setData(List<Entry> datas) {
        this.datas = datas;
        this.notifyDataSetChanged();
    }


}