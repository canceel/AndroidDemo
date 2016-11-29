package com.shenme.androiddemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.general.SearchActivity;
import com.shenme.androiddemo.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CANC on 2016/9/26.
 */

public class CategoryFragment extends BaseFragment {
    @BindView(R.id.ib_search)
    ImageButton ibSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        mContext = getActivity();
        ibSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
