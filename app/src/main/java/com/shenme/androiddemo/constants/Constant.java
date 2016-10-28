package com.shenme.androiddemo.constants;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenme.androiddemo.utils.CacheUtils;
import com.shenme.androiddemo.utils.ConfigBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * <p/>
 * URL调用
 */
public class Constant {

    private static final String CONFIG_PATH = "test.json";
    //系统日志Debug模式开关，默认打开，可以打印日志，发布时设置为false
    public static boolean IS_DEBUG = false;
    // 是否debug
    private static ConfigBean bean = null;

    static {
        File file = CacheUtils.getOrCreateFileInExternalStorage();
        String testStr = file.getAbsolutePath() + File.separator + CONFIG_PATH;
        File testFile = new File(testStr);
        if (testFile != null && testFile.exists()) {
            try {
                bean = new Gson().fromJson(new BufferedReader(new InputStreamReader
                        (new FileInputStream(testFile))), new TypeToken<ConfigBean>() {
                }.getType());
                if (bean != null) {
                    IS_DEBUG = true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static final String BaseHost = IS_DEBUG ? bean.baseHost : "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/";

    public static final String DATE_10 = "10/1";

}
