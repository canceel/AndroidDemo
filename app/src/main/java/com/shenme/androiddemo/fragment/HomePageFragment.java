package com.shenme.androiddemo.fragment;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.adapter.DataAdapter;
import com.shenme.androiddemo.base.BaseFragment;
import com.shenme.androiddemo.bean.Welfare;
import com.shenme.androiddemo.bean.WelfareResult;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.widget.SwipeRefreshLayout;
import com.shenme.androiddemo.widget.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by CANC on 2016/9/26.
 */

public class HomePageFragment extends BaseFragment {

    //界面数据
    private List<Welfare> datas = new ArrayList<>();
    private DataAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.tv_page_info)
    TextView tvPageInfo;
    @BindView(R.id.image)
    SimpleDraweeView simpleDraweeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, view);
        mView = view;
        mContext = getActivity();
        setSwipeLayout();
        initView();
        initPageInfo();
        getData(true);        return view;

    }

    private void initView() {
        adapter = new DataAdapter(mContext);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void getData(boolean isShowDialog) {
        RetrofitUtil.getHttpService().getImage()
                .compose(new RetrofitUtil.CommonOptions<WelfareResult>())
                .subscribe(new CodeHandledSubscriber<WelfareResult>() {
                    @Override
                    protected void onError(ApiException apiException) {

                    }

                    @Override
                    protected void onBusinessNext(WelfareResult data) {
                        if (isRefresh) {
                            datas.clear();
                        }
                        if (data != null && data.getWelfares() != null && data.getWelfares().size() > 0) {
//                            simpleDraweeView.setImageURI(data.getWelfares().get(1).getUrl());
//                            recyclerView.addView(simpleDraweeView);
                            datas.addAll(data.getWelfares());
                            if (adapter == null) {
                                adapter = new DataAdapter(mContext, datas);
                            } else {
                                adapter.setData(datas, mContext);
                            }
                        } else {
                            adapter = new DataAdapter(mContext, null);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCompleted() {
                        swipeRefresh.setRefreshing(false);
                    }
                });
        super.getData(isShowDialog);
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        super.onRefresh(direction);
        swipeLayout.setRefreshing(false);
    }
}
