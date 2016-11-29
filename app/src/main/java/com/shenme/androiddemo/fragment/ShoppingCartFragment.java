package com.shenme.androiddemo.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.shopping.SettlementAvtivity;
import com.shenme.androiddemo.adapter.ShoppingCartAdapter;
import com.shenme.androiddemo.bean.shoppingcart.Entry;
import com.shenme.androiddemo.bean.shoppingcart.ResponseShoppingCart;
import com.shenme.androiddemo.bean.shoppingcart.ShoppingCartResult;
import com.shenme.androiddemo.net.ApiException;
import com.shenme.androiddemo.net.CodeHandledSubscriber;
import com.shenme.androiddemo.net.RetrofitUtil;
import com.shenme.androiddemo.utils.LinkToUtils;
import com.shenme.androiddemo.utils.LoadDialogUtil;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by CANC on 2016/11/7.
 */
public class ShoppingCartFragment extends Fragment implements View.OnClickListener, ShoppingCartAdapter.ShoppingCartListener {
    public static final String UPDATE_CART = "update_cart";

    private RecyclerView recyclerView;
    private TextView settlementTotalPrice;
    private Button settlementCommit;
    private TextView tvSubtotal;


    private List<Entry> datas = new ArrayList<>();
    private ShoppingCartAdapter adapter;
    private Context mContext;
    public static final String TAG = "ShoppingCartFragment";
    private ShoppingCartBroadcastReceiver receive;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        mContext = getActivity();
        ButterKnife.bind((Activity) mContext);
        findView(view);
        initView();
        getData(true);
        registBroadCast();
        return view;
    }

    private void findView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        settlementTotalPrice = (TextView) view.findViewById(R.id.settlement_total_price);
        settlementCommit = (Button) view.findViewById(R.id.settlement_commit);
        tvSubtotal = (TextView) view.findViewById(R.id.tv_subtotal);
        settlementCommit.setOnClickListener(this);
    }

    //初始化
    private void initView() {
        adapter = new ShoppingCartAdapter(mContext, this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    //初始化购物车数据
    public void getData(boolean showDialog) {
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
        if (showDialog) {
            dialog.show();
        }
        RetrofitUtil.getHttpService().getShoppingCart(
                Utils.getUserID(mContext), Utils.getToken(mContext))
                .compose(new RetrofitUtil.CommonOptions<ShoppingCartResult>())
                .subscribe(new CodeHandledSubscriber<ShoppingCartResult>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        ToastUtil.show(apiException.getDisplayMessage());
                    }

                    @Override
                    protected void onBusinessNext(ShoppingCartResult data) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        formatShoppingCart(data.data);
                    }

                    @Override
                    public void onCompleted() {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
    }

    //格式化购物车数据
    private void formatShoppingCart(ResponseShoppingCart data) {
        datas.clear();
        settlementTotalPrice.setText(Utils.PriceFormat(data.getSubTotalPrice()));
        if (data != null && data.entries != null && data.entries.size() > 0) {
            settlementCommit.setClickable(true);
            datas.addAll(data.entries);
            if (adapter == null) {
                adapter = new ShoppingCartAdapter(mContext, datas, this);
            } else {
                adapter.setData(mContext, datas);
            }
        } else {
            settlementCommit.setClickable(false);
            adapter = new ShoppingCartAdapter(mContext, this);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        switch (id) {
            case R.id.settlement_commit:
                intent.setClass(mContext, SettlementAvtivity.class);
                break;
        }
        startActivity(intent);

    }

    /**
     * 根据隐藏与否来判断是否加载最新数据
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            getData(true);
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void isAddCommodityClick(int position) {
        addToCart(mContext, datas.get(position).getProductCode(), "1");
    }

    @Override
    public void isSubCommodityClick(int position) {
        if (Integer.parseInt(datas.get(position).getQuantity()) == 1 || Integer.parseInt(datas.get(position).getQuantity()) < 1) {
            ToastUtil.show("当前商品数量:" + datas.get(position).getQuantity() + ",修改数量失败");
            return;
        } else {
            addToCart(mContext, datas.get(position).getProductCode(), "-1");
        }
    }

    //进入商品详情
    @Override
    public void gotoProductDetail(int position) {
        LinkToUtils.gotoProductDetail(getActivity(), datas.get(position).getProductCode(), true);
    }

    /**
     * 添加到购物车
     */
    private void addToCart(Context context, String productCode, final String qty) {
        Map<String, String> data = new HashMap<>();
        data.put("access_token", Utils.getToken(context));
        data.put("productCode", productCode);
        data.put("qty", qty);
        RetrofitUtil.getHttpService().addProductToCart(
                Utils.getUserID(mContext), data)
                .compose(new RetrofitUtil.CommonOptions<ShoppingCartResult>())
                .subscribe(new CodeHandledSubscriber<ShoppingCartResult>() {
                    @Override
                    protected void onError(ApiException apiException) {
                        ToastUtil.show(apiException.getDisplayMessage());
                    }

                    @Override
                    protected void onBusinessNext(ShoppingCartResult data) {

                        if (Integer.parseInt(qty) == 1) {
                            ToastUtil.show("添加数量成功");
                        } else {
                            ToastUtil.show("减少数量成功");
                        }
                        formatShoppingCart(data.data);
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }


    //注册广播接收者
    private void registBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATE_CART);
        receive = new ShoppingCartBroadcastReceiver();
        mContext.registerReceiver(receive, filter);
    }

    class ShoppingCartBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case UPDATE_CART:
                    getData(false);
                    break;

            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(receive);
    }
}