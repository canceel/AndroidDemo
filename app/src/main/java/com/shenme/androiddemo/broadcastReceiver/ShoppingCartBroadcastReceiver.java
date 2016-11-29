package com.shenme.androiddemo.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by allipper on 2015-12-17.
 */
public class ShoppingCartBroadcastReceiver extends BroadcastReceiver {

    public static final String BC_UPDATE_SHOPPING_CART = "update_shopping_cart";
    public static final String BC_UPDATE_SHOPPING_CART_NUMBER = "update_shopping_cart_number";
    public static final String BC_UPDATE_DRINKMOUDLE_SHOPPING_CART = "update_drink_moudle_shopping_cart";

    private UpdateDrinkMoudleShoppingCartInterface updateDrinkMoudleShoppingCartInterface;
    private UpdateShoppingCartInterface updateShoppingCartInterface;
    private UpdateShoppingCartNumberInterface updateShoppingCartNumberInterface;


    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BC_UPDATE_SHOPPING_CART.equals(action) && updateShoppingCartInterface != null) {
            updateShoppingCartInterface.updateShoppingCart();
        } else if (BC_UPDATE_SHOPPING_CART_NUMBER.equals(action) && updateShoppingCartNumberInterface != null) {
            updateShoppingCartNumberInterface.updateShoppingCartNumber();
        } else if (BC_UPDATE_DRINKMOUDLE_SHOPPING_CART.equals(action) && updateDrinkMoudleShoppingCartInterface != null) {
            updateDrinkMoudleShoppingCartInterface.updateDrinkMoudleShoppingCart();
        }
    }

    public void setUpdateShoppingCartInterface(UpdateShoppingCartInterface updateShoppingCartInterface) {
        this.updateShoppingCartInterface = updateShoppingCartInterface;
    }

    public void setUpdateShoppingCartNumberInterface(UpdateShoppingCartNumberInterface updateShoppingCartNumberInterface) {
        this.updateShoppingCartNumberInterface = updateShoppingCartNumberInterface;
    }

    public void setUpdateDrinkShoppingCartInterface(UpdateDrinkMoudleShoppingCartInterface updateSchoolShoppingCartInterface) {
        this.updateDrinkMoudleShoppingCartInterface = updateSchoolShoppingCartInterface;
    }


    /**
     * 刷新购物车界面
     */
    public interface UpdateShoppingCartInterface {
        void updateShoppingCart();
    }

    /**
     * 刷新购物车数量
     */
    public interface UpdateShoppingCartNumberInterface {
        void updateShoppingCartNumber();
    }

    /**
     * 更新即饮购物车UI
     */
    public interface UpdateDrinkMoudleShoppingCartInterface {
        void updateDrinkMoudleShoppingCart();
    }
}
