package com.shenme.androiddemo.activity.shopping;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.login.LoginActivity;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.broadcastReceiver.ShoppingCartBroadcastReceiver;
import com.shenme.androiddemo.fragment.ShoppingCartFragment;
import com.shenme.androiddemo.utils.Utils;


/**
 * Created by nono on 2016-3-28.
 */
public class ShoppingCartActivity extends BaseActivity {

    public static final String IS_BACK_VISIBLE = "is_back_visible";
    private static final int LOGIN_ACTIVITY = 1000;
    private FragmentManager mFragMgr;
    private ShoppingCartFragment shoppingCartFragment;
    private ShoppingCartBroadcastReceiver receive;
    private boolean isRegister = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mFragMgr = getFragmentManager();
        if (!Utils.isLoginUser(mContext)) {
            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivityForResult(intent, LOGIN_ACTIVITY);
        } else {
            getData();
        }
    }

    //注册广播接收者
    private void registBroadCast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ShoppingCartBroadcastReceiver.BC_UPDATE_SHOPPING_CART);
        receive = new ShoppingCartBroadcastReceiver();
        registerReceiver(receive, filter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (shoppingCartFragment != null) {
            shoppingCartFragment.getData(false);
        }

    }

    private void getData() {
        shoppingCartFragment = new ShoppingCartFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_BACK_VISIBLE, true);
        shoppingCartFragment.setArguments(bundle);
        FragmentTransaction trans = mFragMgr.beginTransaction();
        trans.add(R.id.container, shoppingCartFragment, "SHOPPING_CART");
        trans.commit();
        if (!isRegister) {
            isRegister = true;
            registBroadCast();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                getData();
            } else {
                finish();
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegister) {
            unregisterReceiver(receive);
        }
    }
}
