package com.shenme.androiddemo.activity.general;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.shopping.ShoppingCartActivity;
import com.shenme.androiddemo.adapter.SearchCommodityAdapter;
import com.shenme.androiddemo.base.SwipeRefreshListViewBaseActivity;
import com.shenme.androiddemo.bean.product.ElasticsearchProduct;
import com.shenme.androiddemo.bean.product.FilterList;
import com.shenme.androiddemo.bean.product.ResponseProductList;
import com.shenme.androiddemo.bean.product.SortList;
import com.shenme.androiddemo.broadcastReceiver.ShoppingCartBroadcastReceiver;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.AddHintUtil;
import com.shenme.androiddemo.utils.LinkToUtils;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.MyAnimationUtils;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;
import com.shenme.androiddemo.widget.CircleTextView;
import com.shenme.androiddemo.widget.MyEmptyViewHelper;
import com.shenme.androiddemo.widget.MyFilterPopupWindow;
import com.shenme.androiddemo.widget.MySortPopupWindow;
import com.shenme.androiddemo.widget.SwipyRefreshLayoutDirection;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by king on 2015/7/20.
 */
public class SearchProductActivity extends SwipeRefreshListViewBaseActivity implements View.OnClickListener,
        SearchCommodityAdapter.AddProductListener,
        MySortPopupWindow.OnSortPopupWindowListener,
        MyFilterPopupWindow.FilterListener,
        ShoppingCartBroadcastReceiver.UpdateShoppingCartNumberInterface,
        MyAnimationUtils.GetShoppingCartInterface {

    /**
     * 搜索关键字
     * 1、如果是通过关键字搜索，则为搜索关键字
     * 2、如果是通过分类搜索，则为分类编码
     * 3、如果是通过品牌搜索，则为品牌编码
     */

    //关键字进入商品列表
    public static final String KEY_WORDS = "keywords";
    //点击分类进入商品列表
    public final static String CATEGORY_CODE = "category_code";
    //搜索名称
    public final static String SEARCH_NAME = "searchName";
    //搜索类型
    public final static String KEY_LIST_TYPE = "search_type";
    //通过关键字搜索商品
    public static final int START_BY_SEARCH_KEY = 0;
    //通过类别搜索商品
    public static final int START_BY_CATEGORY = 1;
    //通过品牌搜索商品
    public static final int START_BY_BRAND = 2;

    //商品结果大图展示
    public static final int VIEW_STYLE_GRID = 0;
    //商品结果列表展示
    public static final int VIEW_STYLE_LIST = 1;

    private LinearLayout header;
    private CircleTextView ctvShoppingCartNo;
    private TextView SearchTitleTextView;
    private Button btnDefault;
    private RelativeLayout rlSort;
    private RelativeLayout rlFilter;
    private RelativeLayout rlStock;
    private TextView tvSort;
    private TextView tvFilter;
    private TextView tvStock;
    private ImageButton ibStyle;
    private TextView tvStyle;
    private RelativeLayout rlStyle;
    //FrameLayout控件
    private FrameLayout frameLayout;

    //是否返回有库存的商品，默认为所有商品
    private boolean isAllStcok = false;
    //商品列表视图的样式，默认为列表样式
    private boolean viewType = false;

    // 是否是刷新，刷新需要清空数据
    private ShoppingCartBroadcastReceiver receive;

    private String searchName;
    private ImageButton ib_shopping_cart; //购物车
    private AddHintUtil addHintUtil;


    //咏悦汇
    private String keywords;
    private String sortField;
    private String sortOrder;

    //商品分类编码
    private String categoryCode;
    //排序控件
    private MySortPopupWindow sortPopup;
    //条件过滤控件
    private MyFilterPopupWindow filterPopup;
    private RelativeLayout rl_filterdialog;
    //筛选条件
    private String filterConditionValue;
    //商品数据
    private List<ElasticsearchProduct> products = new ArrayList<>();
    private SearchCommodityAdapter searchCommodityAdapter;
    //服务端返回的筛选数据
    private List<FilterList> filters;
    //服务端返回排序数据
    private List<SortList> sortList;
    private View emptyView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        setSwipeLayout();
        Intent intent = getIntent();
        categoryCode = intent.getStringExtra(CATEGORY_CODE);
        keywords = intent.getStringExtra(KEY_WORDS);
        searchName = intent.getStringExtra(SEARCH_NAME);
        setView();
        registBroadCast();
        getData(true);
    }

    @Override
    protected void getData(final boolean isShowDialog) {
        addHintUtil.removeFootView(listView);
        if (Utils.isNetworkConnected(mContext)) {
            isRequest = true;
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(this);
            if (isShowDialog) {
                dialog.show();
            }
            Map<String, String> data = new HashMap<>();

            if (!TextUtils.isEmpty(String.valueOf(pagination.currentPage))) {
                data.put("page", String.valueOf(pagination.currentPage));

            }
            if (!TextUtils.isEmpty(String.valueOf(pagination.pageSize))) {
                data.put("size", String.valueOf(pagination.pageSize));

            }
            if (!TextUtils.isEmpty(keywords)) {
                data.put("keywords", URLEncoder.encode(keywords));
            }
            if (!TextUtils.isEmpty(sortField)) {
                data.put("sortField", sortField);
            }
            if (!TextUtils.isEmpty(sortOrder)) {
                data.put("sortOrder", sortOrder);
            }
            if (!TextUtils.isEmpty(filterConditionValue)) {
                data.put("", filterConditionValue);
            }


            RetrofitUtil.getHttpService().getProductList(data)
                    .compose(new RetrofitUtil.CommonOptions<ResponseProductList>())
                    .subscribe(new CodeHandledSubscriber<ResponseProductList>() {
                        @Override
                        protected void onError(ApiException apiException) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            swipeLayout.setRefreshing(false);
                            isRequest = false;
                            ToastUtil.show(apiException.getDisplayMessage());
                            emptyView = setEmptyView(swipeLayout, emptyView, MyEmptyViewHelper
                                    .TYPE_NETWORK_ERROR);

                        }

                        @Override
                        protected void onBusinessNext(ResponseProductList data) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            addHintUtil.addFootView(mContext, listView);
                            isRequest = false;
                            if (data.data.pageinfo != null) {
                                pagination = data.data.pageinfo;
                            }
                            swipeLayout.setRefreshing(false);
                            if (pagination.isLastPlPPage())
                                addHintUtil.setState(AddHintUtil.IS_BOTTOM_STATE, null);
                            else addHintUtil.setState(AddHintUtil.NOT_BOTTOM_STATE, null);
                            //筛选数据
                            if ((filters == null)) {
                                if (data.data.fiterList != null) {
                                    filters = data.data.fiterList;
                                }
                            }
                            //排序数据
                            if (sortList == null) {
                                if (data.data.sortList != null) {
                                    sortList = data.data.sortList;
                                }
                            }

                            if (isRefresh || pagination.currentPage == 1) {
                                products.clear();
                            }
                            if (data.data.listinfo != null && data.data.listinfo.size() > 0) {
                                products.addAll(data.data.listinfo);
                            }
                            refreshListView();

                        }

                        @Override
                        public void onCompleted() {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                        }
                    });
        } else {
            emptyView = MyEmptyViewHelper.setEmptyView(swipeLayout, emptyView, MyEmptyViewHelper
                    .TYPE_INDEX_EMPTY_FAILURE);
        }
    }

    //初始化控件
    private void setView() {
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        ib_shopping_cart = (ImageButton) findViewById(R.id.ib_shopping_cart);
        rl_filterdialog = (RelativeLayout) findViewById(R.id.rl_filterdialog);
        header = (LinearLayout) findViewById(R.id.header);
        ctvShoppingCartNo = (CircleTextView) findViewById(R.id.ctv_shopping_cart_no);
        SearchTitleTextView = (TextView) findViewById(R.id.searchTitle);
        tvSort = (TextView) findViewById(R.id.tv_sort);
        btnDefault = (Button) findViewById(R.id.btn_default);
        rlSort = (RelativeLayout) findViewById(R.id.rl_sort);
        rlFilter = (RelativeLayout) findViewById(R.id.rl_filter);
        rlStock = (RelativeLayout) findViewById(R.id.rl_stock);
        tvFilter = (TextView) findViewById(R.id.tv_filter);
        tvStock = (TextView) findViewById(R.id.tv_stock);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        ibStyle = (ImageButton) findViewById(R.id.ib_style);
        tvStyle = (TextView) findViewById(R.id.tv_style);
        rlStyle = (RelativeLayout) findViewById(R.id.rl_style);


        addHintUtil = new AddHintUtil();
        addHintUtil.addFootView(this, listView);
        swipeLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light);

        ib_shopping_cart.setOnClickListener(this);
        btnDefault.setOnClickListener(this);
        rlSort.setOnClickListener(this);
        rlFilter.setOnClickListener(this);
        rlStock.setOnClickListener(this);
        ibStyle.setOnClickListener(this);
        tvStyle.setOnClickListener(this);
        rlStyle.setOnClickListener(this);

        //设置购物车中商品的数量
        updateShoppingCartNo();

        fillView();
    }

    /**
     * 初始化商品列表数据，并设置其点击事件
     */
    private void fillView() {
        if ("".equals(searchName) || searchName == null) {
            SearchTitleTextView.setText("商品列表");
        } else {
            SearchTitleTextView.setText(searchName);
        }

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_default) {
            //当默认被选中后，清除排序，清除筛选，清除有货商品，重新发起请求
            sortField = null;
            sortOrder = null;
            listView.setAdapter(null);
            searchCommodityAdapter = null;
            pagination.currentPage = 1;

            tvSort.setText("排序");
            //清除筛选选中状态
            if (filters != null && filters.size() > 0) {
                for (int i = 0; i < filters.size(); i++) {
                    for (int j = 0; j < filters.get(i).list.size(); j++) {
                        if (filters.get(i).list.get(j).isSelected) {
                            filters.get(i).list.get(j).isSelected = false;
                        }
                    }
                }
            }
            //清除筛选数据
            filterConditionValue = null;


            //返回所有商品
            isAllStcok = false;
            tvStock.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R
                    .mipmap.icon_all), null, null, null);
            isRefresh = true;
            getData(true);
        }
        if (id == R.id.ib_shopping_cart) {
            Intent intent = new Intent();
            intent.setClass(SearchProductActivity.this, ShoppingCartActivity.class);
            startActivityForResult(intent, 0);
        }
        if (id == R.id.rl_sort) {
            showSortPopupWindow(sortList);
        }
        if (id == R.id.rl_filter) {
            showFilterPopupWindow();
        }
        if (id == R.id.rl_stock) {
            isAllStcok = !isAllStcok;
            if (isAllStcok) {
                tvStock.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R
                        .mipmap.icon_instock), null);
            } else {
                tvStock.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R
                        .mipmap.icon_all), null);
            }
            isRefresh = true;
            getData(true);
        }
        if (id == R.id.ib_style || id == R.id.tv_style || id == R.id.rl_style) {
            if (viewType) {
                Animation animation = new AlphaAnimation(0f, 1f);
                animation.setDuration(800);
                listView.setAnimation(animation);
                viewStyle = VIEW_STYLE_LIST;
                tvStyle.setText("列表");
                ibStyle.setImageResource(R.mipmap.icon_search_list_press);
                searchCommodityAdapter.setData(changeData(products));
            } else {
                Animation animation = new AlphaAnimation(0f, 1f);
                animation.setDuration(800);
                listView.setAnimation(animation);
                viewStyle = VIEW_STYLE_GRID;
                tvStyle.setText("大图");
                ibStyle.setImageResource(R.mipmap.icon_search_grid_press);
                searchCommodityAdapter.setData(changeData(products));
            }
            searchCommodityAdapter = new SearchCommodityAdapter(this, changeData(products),
                    viewStyle, this);
            listView.setAdapter(searchCommodityAdapter);
            viewType = !viewType;
        }
    }


    //显示排序的菜单
    private void showSortPopupWindow(List<SortList> sortList) {
        if (sortPopup == null) {
            if (sortList == null) {
                sortPopup = new MySortPopupWindow(this, this, null);
            } else {
                sortPopup = new MySortPopupWindow(this, this, sortList);
            }
        }
        tvSort.setTextColor(getResources().getColor(R.color.light_brown));
        tvSort.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R
                .mipmap.icon_sort_selected), null);
        sortPopup.showAsDropDown(header);
        Animation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(800);
        rl_filterdialog.setAnimation(animation);
        rl_filterdialog.setBackgroundResource(R.color.grey_tr);
        rl_filterdialog.setVisibility(View.VISIBLE);
        sortPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                rl_filterdialog.setVisibility(View.GONE);
                tvSort.setTextColor(getResources().getColor(R.color.text_normal));
                tvSort.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources()
                        .getDrawable(R.mipmap.icon_sort), null);
            }
        });
    }

    //注册广播接收者
    private void registBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ShoppingCartBroadcastReceiver.BC_UPDATE_SHOPPING_CART_NUMBER);
        receive = new ShoppingCartBroadcastReceiver();
        receive.setUpdateShoppingCartNumberInterface(this);
        registerReceiver(receive, filter);
    }

    //显示筛选的菜单
    private void showFilterPopupWindow() {
        filterPopup = new MyFilterPopupWindow(this, filters, this);
        tvFilter.setTextColor(getResources().getColor(R.color.light_brown));
        tvFilter.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R
                .mipmap.icon_filter_selected), null);
        filterPopup.showAsDropDown(header);
        Animation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(800);
        rl_filterdialog.setAnimation(animation);
        rl_filterdialog.setBackgroundResource(R.color.grey_tr);

        rl_filterdialog.setVisibility(View.VISIBLE);
        filterPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                rl_filterdialog.setVisibility(View.GONE);
                tvFilter.setTextColor(getResources().getColor(R.color.text_normal));
                tvFilter.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources()
                        .getDrawable(R.mipmap.icon_filter), null);
            }
        });


    }


    //更新商品列表
    private void refreshListView() {
        if (searchCommodityAdapter == null) {
            searchCommodityAdapter = new SearchCommodityAdapter(this, changeData(products),
                    viewStyle, this);
            listView.setAdapter(searchCommodityAdapter);
        } else {
            searchCommodityAdapter.setData(changeData(products));
        }

        if (frameLayout == null || products.size() == 0) {
            frameLayout.setBackgroundResource(R.color.grey_f2f2f2);
            emptyView = setEmptyView(swipeLayout, emptyView, MyEmptyViewHelper.TYPE_ENDECAPRODUCTRECORDS_EMPTY);
        } else {
            removeEmptyView(swipeLayout, emptyView);
        }
    }


    @Override
    public void addToCart(int position, Activity context, ImageView shoppingcart, ImageView
            product) {
        LinkToUtils.addProductToCart(context, products.get(position).code, "1");

//        MyAnimationUtils.addToShoppingCart(this, shoppingcart, product, products.get(position).code, null, 1, false);
//        MyAnimationUtils.addToShoppingCart(context, shoppingcart, product, products.get(position).code, null, 1, false);
    }

    /**
     * 商品列表转跳商品详情回调
     *
     * @param position
     */
    @Override
    public void linkToProduct(int position) {
        LinkToUtils.gotoProductDetail(mContext, products.get(position).code, false);
//        LinkToUtils.gotoProductDetails(mContext, products.get(position).code, false);
    }

    /**
     * 更新顶部购物车数量
     */

    private void updateShoppingCartNo() {
        int number = Utils.getCartNo(mContext);
        if (number > 0) {
            ctvShoppingCartNo.setVisibility(View.VISIBLE);
            ctvShoppingCartNo.setText(String.valueOf(number));
        } else {
            ctvShoppingCartNo.setVisibility(View.GONE);
            ctvShoppingCartNo.setText("");
        }
    }

    /**
     * 排序的回调接口
     *
     * @param type
     */
    @Override
    public void sortBy(int type) {
        sortField = null;
        sortOrder = null;

        sortField = sortList.get(type).key;
        sortOrder = sortList.get(type).sortStatus;
        tvSort.setText(sortList.get(type).displayName);
        isRefresh = true;
        listView.setAdapter(null);
        searchCommodityAdapter = null;
        pagination.currentPage = 1;
        getData(true);
    }

    /**
     * 筛选的回调接口
     *
     * @param firstPosition
     * @param secondPosition
     */
    @Override
    public void onFilterSelected(int firstPosition, int secondPosition) {
    }

    @Override
    public void onCommit() {
        filterConditionValue = "";
        for (int i = 0; i < filters.size(); i++) {
            for (int j = 0; j < filters.get(i).list.size(); j++) {
                if (filters.get(i).list.get(j).isSelected) {
                    filterConditionValue = filterConditionValue + "&" + filters.get(i).key + "=" + URLEncoder.encode(filters.get(i).list.get(j).name);
                }
            }
        }
        isRefresh = true;
        listView.setAdapter(null);
        searchCommodityAdapter = null;
        pagination.currentPage = 1;
        getData(true);

    }

    @Override
    public void updateShoppingCartNumber() {
        updateShoppingCartNo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receive);
    }

    @Override
    public ImageView getShoppingCart() {
        return ib_shopping_cart;
    }

    public List<TwoProducts> changeData(List<ElasticsearchProduct> products) {
        List<TwoProducts> list = new ArrayList<>();
        if (viewStyle == VIEW_STYLE_GRID) {
            int size = products.size();
            if (size % 2 == 0) {
                for (int i = 0; i < size; i++) {
                    if (i % 2 == 0) {
                        list.add(new TwoProducts(products.get(i), products.get(i + 1)));
                    }
                }
            } else {
                for (int i = 0; i < size - 1; i++) {
                    if (i % 2 == 0) {
                        list.add(new TwoProducts(products.get(i), products.get(i + 1)));
                    }
                }
                list.add(new TwoProducts(products.get(size - 1)));
            }
        } else if (viewStyle == VIEW_STYLE_LIST) {
            for (int i = 0; i < products.size(); i++) {
                list.add(new TwoProducts(products.get(i)));
            }
        }

        return list;
    }


    public class TwoProducts {
        public List<ElasticsearchProduct> list;

        public TwoProducts(ElasticsearchProduct e1, ElasticsearchProduct e2) {
            list = new ArrayList<>();
            list.add(e1);
            list.add(e2);
        }

        public TwoProducts(ElasticsearchProduct e1) {
            list = new ArrayList<>();
            list.add(e1);
        }
    }

}
