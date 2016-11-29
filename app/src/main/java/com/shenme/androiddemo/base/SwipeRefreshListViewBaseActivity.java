package com.shenme.androiddemo.base;


import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.general.SearchProductActivity;
import com.shenme.androiddemo.bean.Pagination;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.widget.SwipeRefreshLayout;
import com.shenme.androiddemo.widget.SwipyRefreshLayoutDirection;


/**
 * 基础类
 * 下拉刷新和上拉加载更多
 * Created by allipper on 2015/9/22.
 */
public abstract class SwipeRefreshListViewBaseActivity extends BaseActivity implements SwipeRefreshLayout
        .OnRefreshListener {
    protected int viewStyle = SearchProductActivity.VIEW_STYLE_LIST;

    // 刷新类swipe
    protected SwipeRefreshLayout swipeLayout;
    // 数据gridView
    protected ListView listView;

    // 是否是刷新，刷新需要清空数据
    protected boolean isRefresh;
    // 分页
    protected Pagination pagination;

    protected TextView tv_page_info;

    protected boolean isRequest = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPageInfo();
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
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
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
        tv_page_info = (TextView) findViewById(R.id.tv_page_info);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                                         int current;
                                         int last;

                                         @Override
                                         public void onScrollStateChanged(AbsListView view, int scrollState) {
                                             if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                                                 tv_page_info.setVisibility(View.GONE);
                                             } else tv_page_info.setVisibility(View.VISIBLE);
                                         }

                                         @Override
                                         public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                                             if (tv_page_info == null)
                                                 return;
                                             if (pagination == null)
                                                 return;
                                             if (viewStyle == SearchProductActivity.VIEW_STYLE_LIST) {
                                                 last = (view.getLastVisiblePosition() + 1);
                                             } else last = (view.getLastVisiblePosition() + 1) * 2;
                                             current = last / pagination.pageSize;
                                             if (last % pagination.pageSize != 0) {
                                                 current++;
                                             }
                                             current = current < pagination.numberOfPages ? current : pagination.numberOfPages;
                                             tv_page_info.setText(current + "/" + pagination.numberOfPages);
                                             if (pagination.currentPage < pagination.numberOfPages && last
                                                     + 4 > (pagination.currentPage) * pagination.pageSize) {
                                                 if (!isRequest) {
                                                     pagination.currentPage++;
                                                     isRefresh = false;
                                                     getData(false);
                                                 }
                                             }
                                         }
                                     }
        );
    }

    /**
     * 请求数据
     *
     * @param isShowDialog 是否要显示等待框
     */
    protected abstract void getData(boolean isShowDialog);

    /**
     * 初始化分页信息
     */
    protected void initPageInfo() {
        pagination = new Pagination();
        pagination.currentPage = 1;
        pagination.pageSize = 16;
    }

    /**
     * 相应刷新时间
     *
     * @param direction
     */
    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {//上拉则是刷新；需要设置页码为1，并且设置是刷新
            isRefresh = true;
            pagination.currentPage = 1;
            getData(false);
        } else {//下拉则是加载更多；需要设置页码加1
            isRefresh = false;
            if (pagination.isLastPlPPage()) {//判断是否是最后一页，最后一页提示没有数据
                swipeLayout.setRefreshing(false);
                ToastUtil.show("没有更多了");
                return;
            }
            if (!isRequest) {
                pagination.currentPage++;
                getData(false);
            }
        }
    }

}
