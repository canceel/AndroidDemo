package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;


import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.address.Address;

import java.util.List;

/**
 * Created by CANC on 2016/3/15.
 */
public class AddressListAdapter extends CommonAdapter<Address> {
    public static final int SHOW_PICK = 0;
    public static final int SHOW_MANAGER = 1;
    private String addressId;

    public AddressListAdapter(Context context, List<Address> datas,
                              String addressId) {
        super(context, datas);
        this.layoutId = R.layout.list_item_manage_address;
        this.addressId = addressId;

    }

    @Override
    public void convert(final ViewHolder holder, Address address) {
        TextView nameTextView = holder.getView(R.id.tv_name);
        TextView phoneTextView = holder.getView(R.id.tv_phone);
        TextView addressTextView = holder.getView(R.id.tv_address);
        final ImageView address_choose = holder.getView(R.id.address_choose);


        //设置地址id
        nameTextView.setText(address.getAddrPk());
        //设置地址
        addressTextView.setText(address.getFullName());
        //设置电话
        phoneTextView.setText(address.getCellphone());
        //不是即饮执行该操作
        if (address.getAddrPk().equals(addressId)) {
            address_choose.setImageResource(R.mipmap.icon_circle_checked);
        } else {
            address_choose.setImageResource(R.mipmap.icon_circle_unchecked);
        }

    }
}
