package com.shenme.androiddemo.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.shenme.androiddemo.R;
import com.shenme.androiddemo.adapter.SortListAdapter;
import com.shenme.androiddemo.bean.product.SortList;

import java.util.List;

/**
 * Created by king on 2015/7/21.
 */
public class MySortPopupWindow extends PopupWindow {
    private SortListAdapter adapter;

    public static final int PRICE_UP = 0;
    public static final int PRICE_DOWN = 1;
    public static final int SALES_UP = 2;
    public static final int SALES_DOWN = 3;

    private TextView priceUp;
    private TextView priceDown;
    private TextView saleUp;
    private TextView saleDown;

    private OnSortPopupWindowListener listener;
    private View conentView;
    private ListView listView;
    private View emptyView;

    public MySortPopupWindow(final Context context, final OnSortPopupWindowListener listener, List<SortList> sortLists) {
        this.listener = listener;
        LayoutInflater inflater = LayoutInflater.from(context);
        conentView = inflater.inflate(R.layout.popupwindow_sort, null);
        listView = (ListView) conentView.findViewById(R.id.sort_list);

        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        if (sortLists != null && sortLists.size() > 0) {
            MyEmptyViewHelper.removeEmptyView(listView, emptyView);
            if (adapter == null) {
                adapter = new SortListAdapter(context, sortLists);
                listView.setAdapter(adapter);
            } else {
                adapter.setData(sortLists);
            }
        } else {
            emptyView = MyEmptyViewHelper.setEmptyView(listView, emptyView,
                    MyEmptyViewHelper.TYPE_SORT_EMPTY);

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.sortBy(i);
                dismiss();
            }
        });

    }

    public interface OnSortPopupWindowListener {
        void sortBy(int type);
    }
}
