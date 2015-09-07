package com.jarvis.android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.FacebookSdk;
import com.jarvis.android.database.AppDb;

/**
 * Main entry for application, this class {@link #onCreate()} will be used to call to setup environment, session, preferences and other pre-UI activities.
 * <p/>
 * The order of initialization calls is important and must not be changed.
 * <p/>
 */
public class JarvisApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        AppDb.init(this);


        // Init Facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    private void getAppVersion() {
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int spaceIndex = pInfo.versionName.indexOf(" ");
            String versionName = spaceIndex >= 0 ? pInfo.versionName.substring(0, pInfo.versionName.indexOf(" ")) : pInfo.versionName;
//            AppPreferences.INSTANCE.saveAppVersion(versionName);
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private static Activity activity;

    public static Activity homeActivity() {
        return activity;
    }

    public static void setHomeActivity(Activity mActivity) {
        activity = mActivity;
    }
}
