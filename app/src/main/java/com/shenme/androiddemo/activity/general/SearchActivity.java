package com.shenme.androiddemo.activity.general;

/**
 * 搜索页
 * Created by Stone on 2015/16/11.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.shenme.androiddemo.R;
import com.shenme.androiddemo.adapter.SearchHistoryAdapter;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.bean.product.SearchHistoryWord;
import com.shenme.androiddemo.database.DbManager;

import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener, AdapterView
        .OnItemClickListener, TextWatcher, TextView.OnEditorActionListener {
    private EditText etSearch;
    private LinearLayout layoutHotSale;
    private ImageButton ibSearchCancel;
    private TextView tvCancel;
    private Button btnHistoryClear;
    private ListView lvHistory;
    private List<SearchHistoryWord> historyWordDatas;
    private SearchHistoryAdapter adapter;
    private DbManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.ib_search_cancel) {
            etSearch.setText("");
        }

        if (id == R.id.tv_cancel) {
            finish();
        }

        if (id == R.id.btn_history_clear) {
            manager.deleteAllSearchHistory();
            historyWordDatas = manager.queryAllSearchHistory();
            adapter.setData(historyWordDatas);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String content = historyWordDatas.get(position).key;
        searchToProduct(content);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            ibSearchCancel.setVisibility(View.VISIBLE);
        } else {
            ibSearchCancel.setVisibility(View.GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //初始化控件
    private void setView() {
        manager = new DbManager(this);
        historyWordDatas = manager.queryAllSearchHistory();

        etSearch = (EditText) findViewById(R.id.et_search);
        ibSearchCancel = (ImageButton) findViewById(R.id.ib_search_cancel);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        btnHistoryClear = (Button) findViewById(R.id.btn_history_clear);
        lvHistory = (ListView) findViewById(R.id.lv_history);
        layoutHotSale = (LinearLayout) findViewById(R.id.layout_hot_sale);

        ibSearchCancel.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        btnHistoryClear.setOnClickListener(this);
        adapter = new SearchHistoryAdapter(this, historyWordDatas);
        lvHistory.setAdapter(adapter);

        lvHistory.setOnItemClickListener(this);
        etSearch.addTextChangedListener(this);
        etSearch.setOnEditorActionListener(this);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) etSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    SearchActivity.this.getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    String content = etSearch.getEditableText().toString();
                    searchToProduct(content);
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 通过关键字进入商品搜索页面
     *
     * @param content
     */
    private void searchToProduct(String content) {
        content = content.replace(" ", "");
        //将搜索关键词设置到EditText中，并将光标移至末尾
        etSearch.setText(content);
        etSearch.setSelection(content.length());

        //新增或更新数据库中的热词
        manager.updateSearchHistotry(content);
        historyWordDatas = manager.queryAllSearchHistory();
        adapter.setData(historyWordDatas);

        Intent intent = new Intent();
        intent.setClass(mContext, SearchProductActivity.class);
        intent.putExtra(SearchProductActivity.SEARCH_NAME, content);
        intent.putExtra(SearchProductActivity.KEY_WORDS, content);
        this.startActivity(intent);

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String content = etSearch.getEditableText().toString();
            searchToProduct(content);
        }
        return false;
    }
}
