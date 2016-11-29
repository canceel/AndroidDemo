package com.shenme.androiddemo.activity.login;

import android.os.Bundle;
import android.webkit.WebView;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/12/19.
 */
public class ServiceAgreementActivity extends BaseActivity {
    private WebView wvAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_agreement);
        wvAgreement = (WebView) findViewById(R.id.wv_agreement);
        wvAgreement.loadDataWithBaseURL(null, readAgreement(), "text/html", "UTF-8", null);
    }

    private String readAgreement() {
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder("");

        String line;
        try {
            inputStream = getAssets().open("agreement.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
