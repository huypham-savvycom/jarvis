package com.jarvis.android.database;


import android.database.sqlite.SQLiteDatabase;

/**
 * All SQL with local database will be called from here.
 * <p/>
 */
public enum DatabaseManager {
    DB;
    private static String TAG = DatabaseManager.class.getName();
    private AppDb.JarvisDb mDbHelper;

    /**
     * This must be called when application starts.
     *
     * @param mDbHelper
     */
    public static void init(AppDb.JarvisDb mDbHelper) {
        DB.mDbHelper = mDbHelper;
    }

    public SQLiteDatabase getReadableDatabase() {
        return mDbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return mDbHelper.getWritableDatabase();
    }

}