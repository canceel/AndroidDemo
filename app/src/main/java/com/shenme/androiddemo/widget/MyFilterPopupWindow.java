package com.shenme.androiddemo.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;


import com.shenme.androiddemo.R;
import com.shenme.androiddemo.adapter.FilterItemViewAdapter;
import com.shenme.androiddemo.bean.product.FilterList;
import com.shenme.androiddemo.utils.Utils;

import java.util.List;

/**
 * Created by king on 2015/7/21.
 */
public class MyFilterPopupWindow extends PopupWindow implements FilterItemViewAdapter
        .FilterFirstListener {

    private Context context;
    private List<FilterList> datas;
    private View emptyView;
    private FilterItemViewAdapter adapter;
    private FilterListener listener;
    private Button btnCommit;

    public MyFilterPopupWindow(Context context,
                               List<FilterList> datas,
                               FilterListener listener) {
        this.context = context;
        this.datas = datas;
        this.listener = listener;

        initListView();
    }

    private void initListView() {
//        View view = LayoutInflater.from(context).inflate(R.layout.activity_search_product, null);
        View view = LayoutInflater.from(context).inflate(R.layout.popupwindow_filter, null);
        ListView filter_listView = (ListView) view.findViewById(R.id.lv_filter);
        btnCommit = (Button) view.findViewById(R.id.btn_commit);

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(filter_listView.getHeight() + 200);
        this.setHeight(Utils.getScreenHight(context) / 2);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        if (datas != null && datas.size() > 0) {
            btnCommit.setVisibility(View.VISIBLE);
            // 设置SelectPicPopupWindow弹出窗体的高
            this.setHeight((Utils.getScreenHight(context) / 5) * 3);
            MyEmptyViewHelper.removeEmptyView(filter_listView, emptyView);
            adapter = new FilterItemViewAdapter(context, datas, this);
            filter_listView.setAdapter(adapter);
        } else {
            // 设置SelectPicPopupWindow弹出窗体的高
            this.setHeight((Utils.getScreenHight(context) / 2));
            emptyView = MyEmptyViewHelper.setEmptyView(filter_listView, emptyView,
                    MyEmptyViewHelper.TYPE_FILTER_EMPTY);
        }
    }

    @Override
    public void filterFirstIsSelected(int firstPosition, int secondPosition) {
        for (int j = 0; j < datas.get(firstPosition).list.size(); j++) {
            if (datas.get(firstPosition).list.get(j).isSelected && secondPosition == j) {
                datas.get(firstPosition).list.get(j).isSelected = false;
            } else {
                datas.get(firstPosition).list.get(j).isSelected =
                        (secondPosition == j);
            }
        }

        adapter.setData(datas);
        listener.onFilterSelected(firstPosition, secondPosition);

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onCommit();
                    dismiss();
                }
            }
        });
    }

    public interface FilterListener {
        void onFilterSelected(int firstPosition, int secondPosition);

        void onCommit();
    }
}
