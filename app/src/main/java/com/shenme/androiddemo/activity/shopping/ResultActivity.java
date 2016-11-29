package com.shenme.androiddemo.activity.shopping;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.base.BaseActivity;


/**
 * Created by CANC on 2016/11/10.
 */

public class ResultActivity extends BaseActivity {
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    private TextView status;
    private TextView message;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        status = (TextView) findViewById(R.id.status);
        message = (TextView) findViewById(R.id.message);
        btnBack = (Button) findViewById(R.id.btn_back);

        status.setText(getIntent().getStringExtra(STATUS));
        message.setText(getIntent().getStringExtra(MESSAGE));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
