package com.shenme.androiddemo.database;

import android.provider.SyncStateContract;

/**
 * Created by Stone on 2015/16/11.
 * Project:YongHuiProject
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class SearchHistoryEntry implements SyncStateContract.Columns {
    public static final String TABLE_NAME = "search_history";
    public static final String COLUMN_SEARCH_KEY = "search_key";
    public static final String COLUMN_TIMESTAMP = "timestamp";
}
