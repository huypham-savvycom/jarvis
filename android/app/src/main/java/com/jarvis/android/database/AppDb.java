package com.jarvis.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jarvis.android.R;

/**
 * Database of app
 */
public enum AppDb {

    DB;
    private static String TAG = AppDb.class.getName();
    public static final String NAME = "jarviscached.db";
    private static String PATH = "";
    public static final int VERSION = 1;

    private JarvisDb mDbHelper;

    /**
     * Creates or preloads database on startup.
     *
     * @param context
     */
    public static void init(Context context) {
        if (DB.mDbHelper == null) {
            PATH = String.format(context.getResources().getString(R.string.path_database), context.getPackageName());
            DB.mDbHelper = new JarvisDb(context);
            DB.mDbHelper.createDataBase();
            DB.mDbHelper.openDataBase();
            DatabaseManager.init(DB.mDbHelper);
        }
    }

    public static class JarvisDb extends SQLiteOpenHelper {

        private SQLiteDatabase mSQlite;

        public JarvisDb(Context context) {
            super(context, NAME, null, VERSION);
        }

        /**
         * Creates a empty database on the system and rewrites it with your own
         * database.
         */
        public void createDataBase() {
            this.getReadableDatabase();
        }

        public void openDataBase() {
            close();
            // Open the database
            String myPath = PATH + NAME;
            mSQlite = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }

        @Override
        public synchronized void close() {
            if (mSQlite != null) {
                mSQlite.close();
                mSQlite = null;
            }
            super.close();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.beginTransaction();
            try {
                db.execSQL(Schema.CREATE_PAGE_TABLE);
                db.execSQL(Schema.CREATE_POST_TABLE);
                db.execSQL(Schema.CREATE_COMMENT_TABLE);
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Schema.PAGE_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.POST_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.COMMENT_NAME_TABLE + ";");
            onCreate(db);
        }
    }
}
