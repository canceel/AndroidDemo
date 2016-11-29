package com.shenme.androiddemo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.login.LoginActivity;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.fragment.CategoryFragment;
import com.shenme.androiddemo.fragment.HomePageFragment;
import com.shenme.androiddemo.fragment.MineFragment;
import com.shenme.androiddemo.fragment.ShoppingCartFragment;
import com.shenme.androiddemo.utils.LinkToUtils;
import com.shenme.androiddemo.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    public static final String BC_UPDATE_USER_INFO = "update_user_info";
    public static final String BC_UPDATE_ORDER_NUMBER = "update_order_number";
    public static final String BC_UPDATE_CURRENT_CITY = "update_current_city";
    public static final String GOTO_CART = "goto_cart";
    public static final String GOTO_CATEGORY = "goto_category";
    public static final String GOTO_MINE = "goto_mine";
    public static final String GOTO_HOME = "goto_home";
    private static final int IS_LOGIN = 5;

    @BindView(R.id.bottomNavigationBar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    private HomePageFragment homePageFragment;
    private CategoryFragment categoryFragment;
    private ShoppingCartFragment shoppingCartFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor(R.color.red)
                .setInActiveColor(R.color.black)
                .setBarBackgroundColor(R.color.white_229_229_229)
                .addItem(new BottomNavigationItem(R.mipmap.icon_tab_home_red, "首页").setInactiveIconResource(R.mipmap.icon_tab_home))
                .addItem(new BottomNavigationItem(R.mipmap.icon_tab_category_red, "分类").setInactiveIconResource(R.mipmap.icon_tab_category))
                .addItem(new BottomNavigationItem(R.mipmap.icon_tab_shopping_cart_red, "购物车").setInactiveIconResource(R.mipmap.icon_tab_shopping_cart))
                .addItem(new BottomNavigationItem(R.mipmap.icon_tab_user_red, "个人中心").setInactiveIconResource(R.mipmap.icon_tab_user))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    //默认fragment
    private void setDefaultFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        homePageFragment = new HomePageFragment();
        fragmentTransaction.add(R.id.fragment_container, homePageFragment);
        fragmentTransaction.commit();

    }

    /**
     * 隐藏fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (homePageFragment != null) {
            fragmentTransaction.hide(homePageFragment);
        }
        if (categoryFragment != null) {
            fragmentTransaction.hide(categoryFragment);
        }
        if (shoppingCartFragment != null) {
            fragmentTransaction.hide(shoppingCartFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }

    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (position) {
            case 0:
                if (homePageFragment == null) {
                    homePageFragment = new HomePageFragment();
                    fragmentTransaction.add(R.id.fragment_container, homePageFragment);
                } else {
                    fragmentTransaction.show(homePageFragment);
                }
                break;
            case 1:
                if (categoryFragment == null) {
                    categoryFragment = new CategoryFragment();
                    fragmentTransaction.add(R.id.fragment_container, categoryFragment);
                } else {
                    fragmentTransaction.show(categoryFragment);
                }
                break;
            case 2:
                if (!Utils.isLoginUser(mContext)) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.putExtra(LoginActivity.IS_MINE_FRAGMENT_LOGIN, true);
                    startActivityForResult(intent, IS_LOGIN);
                } else {
                    if (shoppingCartFragment == null) {
                        shoppingCartFragment = new ShoppingCartFragment();
                        fragmentTransaction.add(R.id.fragment_container, shoppingCartFragment);
                    } else {
                        fragmentTransaction.show(shoppingCartFragment);
                    }
                }
                break;
            case 3:
                if (!LinkToUtils.checkAndLoginActivity(mContext)) {
                    if (mineFragment == null) {
                        mineFragment = new MineFragment();
                        fragmentTransaction.add(R.id.fragment_container, mineFragment);
                    } else {
                        fragmentTransaction.show(mineFragment);
                    }

                }
                break;
        }
        fragmentTransaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
