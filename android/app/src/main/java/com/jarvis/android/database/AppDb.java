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
                db.execSQL(Schema.CREATE_ALBUM_TABLE);
                db.execSQL(Schema.CREATE_BUSINESS_TABLE);
                db.execSQL(Schema.CREATE_CONTACT_TABLE);
                db.execSQL(Schema.CREATE_CUSTOMER_PROFILES_TABLE);
                db.execSQL(Schema.CREATE_DEAL_TABLE);
                db.execSQL(Schema.CREATE_MAIN_CATEGORY_TABLE);
                db.execSQL(Schema.CREATE_VENDER_CATEGORY_TABLE);
                db.execSQL(Schema.CREATE_MERCHANT_TABLE);
                db.execSQL(Schema.CREATE_MESSAGE_TABLE);
                db.execSQL(Schema.CREATE_NON_ZAKA_MERCHANT_TABLE);
                db.execSQL(Schema.CREATE_NOTIFICATION_TABLE);
                db.execSQL(Schema.CREATE_REFERRAL_TABLE);
                db.execSQL(Schema.CREATE_SUB_CATEGORY_TABLE);
                db.execSQL(Schema.CREATE_USER_TABLE);
                db.execSQL(Schema.CREATE_DB_INFO_TABLE);
                db.execSQL(Schema.CREATE_FRIEND_REQUEST_TABLE);
                db.execSQL(Schema.CREATE_NEWS_FEED_TABLE);

                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Schema.ALBUM_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.BUSINESS_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.CONTACT_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.CUSTOMER_PROFILE_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.DEAL_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.MAIN_CATEGORY_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.VENDER_CATEGORY_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.MERCHANT_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.MESSAGE_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.NON_ZAKA_MERCHANT_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.NOTIFICATION_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.REFERRAL_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.SUB_CATEGORY_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.USER_NAME_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.DB_INFO_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.DB_FRIEND_REQUEST_TABLE + ";");
            db.execSQL("DROP TABLE IF EXISTS " + Schema.NEWS_FEED_TABLE + ";");
            onCreate(db);
        }
    }
}
