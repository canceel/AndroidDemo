package com.shenme.androiddemo.base;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chanven.lib.cptr.header.MaterialProgressDrawable;
import com.jakewharton.rxbinding.view.RxView;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.Pagination;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.widget.SwipeRefreshLayout;
import com.shenme.androiddemo.widget.SwipyRefreshLayoutDirection;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;


/**
 * Created by CANC on 2016/9/10.
 */
public class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //刷新类
    protected SwipeRefreshLayout swipeLayout;
    //页码
    protected Pagination pagination;
    //数据listview
    protected RecyclerView recyclerView;
    //页码提示
    protected TextView tv_page_info;
    protected Context mContext;
    //是否需要清空数据
    protected boolean isRefresh = true;
    //是否正在请求
    protected boolean isRequest = true;
    //
    protected View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        mContext = getActivity();
        return onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {//上拉则是刷新；需要设置页码为0，并且设置是刷新
            isRefresh=true;
            pagination.currentPage = 0;
            getData(false);
        } else {//下拉则是加载更多；需要设置页码加1
            isRefresh=false;
            if (pagination.isLastPlPPage()) {//判断是否是最后一页，最后一页提示没有数据
                swipeLayout.setRefreshing(false);
                ToastUtil.show("没有更多");
                return;
            }
            pagination.currentPage = pagination.currentPage + 1;
            getData(false);

        }
    }

    //防抖动
    public void setClicks(final View v) {
        RxView.clicks(v)
                .throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Clicks(v);
            }
        });
    }

    public void Clicks(View view) {
    }

    /**
     * 获取数据
     *
     * @param isShowDialog
     */
    protected void getData(boolean isShowDialog) {
    }


    /**
     * 初始化分页信息
     */
    protected void initPageInfo() {
        pagination = new Pagination();
        pagination.currentPage = 0;
        pagination.pageSize = 10;
    }


    /**
     * 设置swipeView 并设置刷新方向
     */
    protected void setSwipeLayout() {
        setSwipeLayout(null);
    }

    /**
     * 设置swipeView 并设置刷新方向
     *
     * @param direction
     */
    protected void setSwipeLayout(SwipyRefreshLayoutDirection direction) {
        swipeLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe_refresh);
        swipeLayout.setOnRefreshListener(this);
        if (direction == null) {
            swipeLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        } else {
            swipeLayout.setDirection(direction);
        }
        // set style
        swipeLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color
                        .holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        swipeLayout.setSize(MaterialProgressDrawable.LARGE);
        recyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        tv_page_info = (TextView) mView.findViewById(R.id.tv_page_info);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int current, last;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int i, int i1) {
                if (tv_page_info == null) {
                    return;
                }
                if (pagination == null) {
                    return;
                }
                last = mLayoutManager.findLastVisibleItemPosition();
                current = last / pagination.pageSize;
                if (last % pagination.pageSize != 0) {
                    current++;
                }
                current = current < pagination.numberOfPages ? current : pagination.numberOfPages;
                tv_page_info.setText(current + "/" + pagination.numberOfPages);
                if (pagination.currentPage < pagination.numberOfPages && last + 4 > (pagination.currentPage) * pagination.pageSize) {
                    if (!isRequest) {
                        pagination.currentPage++;
                        isRefresh = false;
                        getData(false);
                    }
                }

            }
        });

    }
}
