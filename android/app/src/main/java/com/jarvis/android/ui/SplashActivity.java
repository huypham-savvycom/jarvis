package com.jarvis.android.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;

import com.jarvis.android.R;
import com.jarvis.android.utils.SdkUtils;

import java.io.File;

public class SplashActivity extends BaseActivity {

    private final String TAG = SplashActivity.class.getName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        SdkUtils.enableSystemTranslucent(this);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                openView();
            }
        }, 1200);

    }

    private void openView() {
//        if (AppPreferences.INSTANCE.isFirstStart()) {
//            deleteCache(getApplicationContext());
//            AppPreferences.INSTANCE.setFirstStart(false);
//        }
//        if (AppSession.CURRENT.isAuthenticated()) {
//            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
//        } else {
//            AppSession.CURRENT.logUserOut();
//            startActivity(new Intent(SplashActivity.this, IHLSignupChoiceActivity.class));
//        }
        finish();
    }

    /*
     * Delete cache of user data.
     */
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            Log.e("ERROR: deleteCache()", "Unable to delete cache.");
        }
    }

    /*
     * Delete directory of the application directory recursively.
     */
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
