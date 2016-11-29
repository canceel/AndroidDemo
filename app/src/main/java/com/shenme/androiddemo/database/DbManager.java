package com.shenme.androiddemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.shenme.androiddemo.bean.product.SearchHistoryWord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stone on 2016/3/16.
 * Project:haiwaigo
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class DbManager {
    private static DbHelper helper = null;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        getDbHelper(context);
    }

    private static synchronized void getDbHelper(Context context) {
        if (helper == null) {
            helper = new DbHelper(context);
        }
    }

    /**
     * 更新历史搜索表
     *
     * @param word 搜索词类
     */
    public synchronized void updateSearchHistotry(String word) {
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(SearchHistoryEntry.COLUMN_SEARCH_KEY, word);
            values.put(SearchHistoryEntry.COLUMN_TIMESTAMP, System.currentTimeMillis());
            db.replace(SearchHistoryEntry.TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 查询所有的历史搜索记录
     *
     * @return
     */
    public List<SearchHistoryWord> queryAllSearchHistory() {
        db = helper.getReadableDatabase();
        db.beginTransaction();
        List<SearchHistoryWord> datas = new ArrayList<>();

        String[] columns = {SearchHistoryEntry.COLUMN_SEARCH_KEY, SearchHistoryEntry
                .COLUMN_TIMESTAMP};
        String orderBy = SearchHistoryEntry.COLUMN_TIMESTAMP + " DESC ";
        Cursor cursor = db.query(true, SearchHistoryEntry.TABLE_NAME, columns, null, null, null,
                null,
                orderBy, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            SearchHistoryWord historyWord = new SearchHistoryWord();
            historyWord.key = cursor.getString(cursor.getColumnIndex(SearchHistoryEntry
                    .COLUMN_SEARCH_KEY));
            historyWord.timestamp = cursor.getLong(cursor.getColumnIndex(SearchHistoryEntry
                    .COLUMN_TIMESTAMP));
            datas.add(historyWord);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return datas;
    }

    /**
     * 删除历史搜索记录
     */
    public void deleteAllSearchHistory() {
        db = helper.getReadableDatabase();
        db.beginTransaction();
        db.delete(SearchHistoryEntry.TABLE_NAME, null, null);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

}
