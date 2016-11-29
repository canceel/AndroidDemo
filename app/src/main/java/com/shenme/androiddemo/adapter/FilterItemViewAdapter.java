package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.widget.GridView;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.product.FilterList;

import java.util.List;

/**
 * Created by king on 2015/7/29.
 */
public class FilterItemViewAdapter extends CommonAdapter<FilterList> implements
        PickUpFilterItemViewAdapter.SelectorListerner {
    private FilterFirstListener listener;

    public FilterItemViewAdapter(Context context,
                                 List<FilterList> datas,
                                 FilterFirstListener listener) {
        super(context, datas);
        this.layoutId = R.layout.list_item_popup_filter;
        this.listener = listener;
    }

    @Override
    public void convert(ViewHolder holder, FilterList filter) {
        TextView title = holder.getView(R.id.tv_title);
        title.setText(filter.displayName);
        GridView itemGridView = holder.getView(R.id.gridView);
        PickUpFilterItemViewAdapter adapter = new PickUpFilterItemViewAdapter(context,
                filter.list, this, holder.position);
        itemGridView.setAdapter(adapter);
    }

    @Override
    public void updateSelectorMap(int firstPosition, int secondPosition) {
        listener.filterFirstIsSelected(firstPosition, secondPosition);
    }

    public interface FilterFirstListener {
        void filterFirstIsSelected(int firstPosition, int secondPosition);
    }
}
