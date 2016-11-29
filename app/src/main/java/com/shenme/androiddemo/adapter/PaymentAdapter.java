package com.shenme.androiddemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.shenme.androiddemo.R;
import com.shenme.androiddemo.bean.shoppingcart.PaymentMode;

import java.util.List;

/**
 * Created by CANC on 2016/11/28.
 */
public class PaymentAdapter extends CommonAdapter<PaymentMode> {
    private PaymentSelectListener listener;
    private String paymentCode;

    public PaymentAdapter(Context context, List<PaymentMode> datas, String paymentCode,
                          PaymentSelectListener
                                  listener) {
        super(context, datas);
        this.layoutId = R.layout.pay_lib_list_item_payment;
        this.paymentCode = paymentCode;
        this.listener = listener;
        for (PaymentMode payment : datas) {
            if (payment.getPaymentCode().equals(paymentCode)) {
                payment.isSelect = true;
            }
        }
    }


    @Override
    public void convert(final ViewHolder holder, final PaymentMode payment) {
        RelativeLayout layoutPayment = holder.getView(R.id.layout_payment);
        ImageView cbPaymentCheck = holder.getView(R.id.payment_check);
        ImageView ivPaymentIcon = holder.getView(R.id.payment_icon);
        TextView tvPaymentText = holder.getView(R.id.payment_text);
        payment.isSelect = payment.getPaymentCode().equals(paymentCode);

        if (payment.isSelect) {
            cbPaymentCheck.setImageResource(R.mipmap.icon_circle_checked);
        } else {
            cbPaymentCheck.setImageResource(R.mipmap.icon_circle_unchecked);
        }

        ivPaymentIcon.setBackgroundResource(R.mipmap.pay_lib_icon_ailpay);


        layoutPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < datas.size(); i++) {
                    PaymentMode payment = datas.get(i);
                    if (holder.position == i) {
                        payment.isSelect = true;
                        listener.selectPayment(payment);
                    } else {
                        payment.isSelect = false;
                    }
                }
                notifyDataSetChanged();
            }
        });
        tvPaymentText.setText(payment.getPaymentName());
    }

    public void setDefault(String code) {
        for (PaymentMode mode : datas) {
            if (code.equals(mode.getPaymentCode())) {
                mode.isSelect = true;
                break;
            }
        }
    }

    public interface PaymentSelectListener{
        void selectPayment(PaymentMode payment);
    }
}
