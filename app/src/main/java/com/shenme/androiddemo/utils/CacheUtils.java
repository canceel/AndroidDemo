package com.shenme.androiddemo.utils;

import android.os.Environment;

import java.io.File;

/**
 * 应用缓存管理工具
 * Created by Stone on 2016/1/18.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class CacheUtils {

    /**
     * 应用的文件缓存路劲
     */
    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getPath()
            + File.separator
            + "g.yonghuigo.com";

    //创建缓存目录
    public static File getOrCreateFileInExternalStorage() {
        File cacheDir = new File(CACHE_PATH);
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }
        return cacheDir;
    }

    //清除本地缓存
    public static void clearCache() {
        File file = getOrCreateFileInExternalStorage();
        //清除头像文件缓存
        CropUtils.clear(file);
    }
}
