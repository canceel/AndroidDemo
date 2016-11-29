package com.shenme.androiddemo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/7/9.
 */
public class DbHelper extends SQLiteOpenHelper {


    /**
     * 数据库名称 *
     */
    public static final String DATABASE_NAME = "yonghuigo.db";

    /**
     * 数据库版本 *
     */
    public static final int DATABASE_VERSION = 1;


    /**
     * 创建历史搜索表
     */
    public static final String SQL_CREATE_ENTRIES_SEARCH_HISTORY = "CREATE TABLE "
            + SearchHistoryEntry.TABLE_NAME + " (" + SearchHistoryEntry._ID
            + " INTEGER PRIMARY KEY," + SearchHistoryEntry.COLUMN_SEARCH_KEY
            + " TEXT NOT NULL," + SearchHistoryEntry.COLUMN_TIMESTAMP
            + " INTEGER, UNIQUE("
            + SearchHistoryEntry.COLUMN_SEARCH_KEY + "))";


    /**
     * 删除历史搜索表
     */
    public static final String SQL_DELETE_ENTRIES_SEARCH_HISTORY = "DROP TABLE IF EXISTS "
            + SearchHistoryEntry.TABLE_NAME;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_SEARCH_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_SEARCH_HISTORY);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_SEARCH_HISTORY);
        onCreate(db);
    }
}
