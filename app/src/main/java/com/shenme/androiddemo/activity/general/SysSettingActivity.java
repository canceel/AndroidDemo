package com.shenme.androiddemo.activity.general;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.MainActivity;
import com.shenme.androiddemo.base.BaseActivity;
import com.shenme.androiddemo.utils.DataCleanManager;
import com.shenme.androiddemo.utils.SharedPre;
import com.shenme.androiddemo.utils.SharedPreUtils;
import com.shenme.androiddemo.utils.ToastUtil;
import com.shenme.androiddemo.utils.Utils;
import com.umeng.message.PushAgent;


/**
 * 系统设置
 * Created by Administrator on 2015/7/11.
 */
public class SysSettingActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = SysSettingActivity.class.getSimpleName();
    public static final String EXIT_CURRENT_USER = "exit_current_user";

    private TextView tvAppVersions;
    private Button btnAbout;
    private Button btnCheckVersion;
    private Button btnClearCache;
    private Button btnExit;
    private TextView cacheSize;
    private int versionCode = 1;
    private boolean isMessagePushOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_system_setting);
        tvAppVersions = (TextView) findViewById(R.id.app_versions);
        btnAbout = (Button) findViewById(R.id.about);
        btnCheckVersion = (Button) findViewById(R.id.check_version);
        btnClearCache = (Button) findViewById(R.id.clear_cache);
        btnExit = (Button) findViewById(R.id.exit);
        cacheSize = (TextView) findViewById(R.id.cache_size);
        btnAbout.setOnClickListener(this);
        btnClearCache.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        isMessagePushOn = SharedPreUtils.getBoolean(mContext, SharedPre.App.MESSAGE_PUSH, true);
        btnCheckVersion.setOnClickListener(this);
        getAppversions();
        //获取缓存大小
        try {
            cacheSize.setText(DataCleanManager.getCacheSize(mContext.getCacheDir()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void back(View v) {
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (R.id.about == id) {
            ToastUtil.show("关于我们");
//            Intent intentAbout = new Intent(SysSettingActivity.this, AboutActivity.class);
//            startActivity(intentAbout);
        }
        if (R.id.exit == id) {
            new AlertDialog.Builder(this, R.style.CommonDialog).setMessage("确定要注销当前账号吗？")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {


                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //删除SharedPreference中的用户信息
                            Utils.cleanUserInfo(SysSettingActivity.this);
//                            //更新用户信息
//                            sendBroadcast(new Intent(IndexActivity.BC_UPDATE_USER_INFO));
//                            //更新个人中心订单数量
//                            sendBroadcast(new Intent(IndexActivity.BC_UPDATE_ORDER_NUMBER));
//                            //更新购物车数量
//                            sendBroadcast(new Intent(ShoppingCartBroadcastReceiver.BC_UPDATE_SHOPPING_CART_NUMBER));
                            Intent intent = new Intent(SysSettingActivity.this, MainActivity
                                    .class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra(EXIT_CURRENT_USER, true);
                            startActivity(intent);
                        }
                    }).setPositiveButton("取消", null).show();
        }
        if (id == R.id.check_version) {
            ToastUtil.show("检查更新");
        }
        if (id == R.id.clear_cache) {
            new AlertDialog.Builder(this, R.style.CommonDialog).setMessage("确定要清除缓存吗？")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                ToastUtil.show("已清理缓存:" + DataCleanManager.getCacheSize(mContext.getCacheDir()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            DataCleanManager.cleanInternalCache(mContext);
                            DataCleanManager.cleanExternalCache(mContext);
                            //清除缓存后重新获取缓存大小
                            try {
                                cacheSize.setText(DataCleanManager.getCacheSize(mContext.getCacheDir()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).setPositiveButton("取消", null).show();
        }

    }

    private void getAppversions() {
        PackageManager manager;
        PackageInfo info;
        manager = this.getPackageManager();
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            tvAppVersions.setText("版本号：" + info.versionName);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
