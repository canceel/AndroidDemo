package com.shenme.androiddemo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.shoppingcart.Entry;

import java.util.List;

/**
 * Created by CANC on 2016/11/7.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    List<Entry> datas;
    private LayoutInflater inflater;
    private ShoppingCartListener shoppingCartListener;

    public ShoppingCartAdapter(Context context, ShoppingCartListener shoppingCartListener) {
        this(context, null, shoppingCartListener);
    }

    public ShoppingCartAdapter(Context context, List<Entry> datas, ShoppingCartListener shoppingCartListener) {
        this.mContext = context;
        this.datas = datas;
        this.shoppingCartListener = shoppingCartListener;
        inflater = LayoutInflater.from(mContext);
    }

    public void setData(Context context, List<Entry> data) {
        this.datas = data;
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.adapter_shopping_cart, viewGroup, false);
        RecyclerView.ViewHolder viewHolder = new ShoppingCartViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int i) {
        ((ShoppingCartViewHolder) viewHolder).commodityName.setText(datas.get(i).getProductName());
        ((ShoppingCartViewHolder) viewHolder).commodityCurrentCrice.setText(datas.get(i).getActPrice());
        ((ShoppingCartViewHolder) viewHolder).commodityNumber.setText(datas.get(i).getQuantity());
        if (Integer.parseInt(datas.get(i).getQuantity()) == 1 || Integer.parseInt(datas.get(i).getQuantity()) < 1) {
            ((ShoppingCartViewHolder) viewHolder).commoditySub.setClickable(false);
        } else {
            ((ShoppingCartViewHolder) viewHolder).commoditySub.setClickable(true);
        }
        ((ShoppingCartViewHolder) viewHolder).commoditOriginalPrice.setText(datas.get(i).getBasePrice());
        ((ShoppingCartViewHolder) viewHolder).totalPrice.setText(datas.get(i).getTotalPrice());
        ((ShoppingCartViewHolder) viewHolder).simpleDraweeView.setImageURI(datas.get(i).getImageUrl());

        ((ShoppingCartViewHolder) viewHolder).commoditySub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingCartListener.isSubCommodityClick(viewHolder.getPosition());
            }
        });
        ((ShoppingCartViewHolder) viewHolder).commodityAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingCartListener.isAddCommodityClick(viewHolder.getPosition());
            }
        });
        ((ShoppingCartViewHolder) viewHolder).layoutCommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingCartListener.gotoProductDetail(viewHolder.getPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layoutCommodity;
        private TextView commodityName;
        private TextView commodityCurrentCrice;
        private TextView commoditOriginalPrice;
        private TextView totalPrice;
        private ImageButton commoditySub;
        private TextView commodityNumber;
        private ImageButton commodityAdd;
        private SimpleDraweeView simpleDraweeView;

        public ShoppingCartViewHolder(final View itemView) {
            super(itemView);
            layoutCommodity = (RelativeLayout) itemView.findViewById(R.id.layout_commodity);
            commodityName = (TextView) itemView.findViewById(R.id.commodity_name);
            commodityCurrentCrice = (TextView) itemView.findViewById(R.id.commodity_current_price);
            commoditOriginalPrice = (TextView) itemView.findViewById(R.id.commodity_original_price);
            totalPrice = (TextView) itemView.findViewById(R.id.total_price);
            commoditySub = (ImageButton) itemView.findViewById(R.id.commodity_sub);
            commodityNumber = (TextView) itemView.findViewById(R.id.commodity_number);
            commodityAdd = (ImageButton) itemView.findViewById(R.id.commodity_add);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.iv_icon);
        }
    }

    public interface ShoppingCartListener {
        void isAddCommodityClick(int position);

        void isSubCommodityClick(int position);

        void gotoProductDetail(int position);
    }

}
