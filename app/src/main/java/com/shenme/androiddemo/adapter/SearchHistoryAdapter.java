package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.widget.TextView;


import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.product.SearchHistoryWord;

import java.util.List;


/**
 * Created by Stone on 2015/11/23.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class SearchHistoryAdapter extends CommonAdapter<SearchHistoryWord> {

    public SearchHistoryAdapter(Context context, List<SearchHistoryWord> datas) {
        super(context, datas);
        this.layoutId = R.layout.adapter_search_history;
    }

    @Override
    public void convert(ViewHolder holder, SearchHistoryWord searchHistoryWord) {
        TextView textView = holder.getView(R.id.tv_history_word);
        textView.setText(searchHistoryWord.key);
    }
}
