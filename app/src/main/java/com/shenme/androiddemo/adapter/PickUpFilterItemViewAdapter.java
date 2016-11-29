package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.product.ProductListFilter;

import java.util.List;

/**
 * Created by king on 2015/7/29.
 */

public class PickUpFilterItemViewAdapter extends CommonAdapter<ProductListFilter> {
    private SelectorListerner listener;
    private int position;

    public PickUpFilterItemViewAdapter(Context context,
                                       List<ProductListFilter> datas,
                                       SelectorListerner listener,
                                       int position) {
        super(context, datas);
        this.layoutId = R.layout.gridview_item_popup_filter;
        this.listener = listener;
        this.position = position;
    }

    @Override
    public void convert(final ViewHolder holder, final ProductListFilter item) {
        final TextView tvFilter = holder.getView(R.id.tv_filter);
        tvFilter.setText(item.name + "(" + item.value + ")");

        if (item.isSelected) {
            tvFilter.setBackgroundResource(R.drawable.button_radiu_style);
            tvFilter.setTextColor(context.getResources().getColor(R.color.white));
        } else {
            tvFilter.setBackgroundResource(R.drawable.background_radius_white_boder_yellow);
            tvFilter.setTextColor(context.getResources().getColor(R.color.yellow_bf9f62));
        }

        tvFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.updateSelectorMap(position, holder.position);
            }
        });

    }

    public interface SelectorListerner {
        void updateSelectorMap(int firstPosition, int secondPosition);
    }
}
