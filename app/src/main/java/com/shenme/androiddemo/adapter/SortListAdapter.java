package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.product.SortList;

import java.util.List;

/**
 * Created by CANC on 2016/7/7.
 */
public class SortListAdapter extends CommonAdapter<SortList> {
    public SortListAdapter(Context context, List<SortList> datas) {
        super(context, datas);
        this.layoutId = R.layout.list_item_sort;
    }

    @Override
    public void convert(final ViewHolder holder, SortList sortList) {
        TextView tvSortItem = holder.getView(R.id.sort_item);
        tvSortItem.setText(sortList.displayName);

    }

}
