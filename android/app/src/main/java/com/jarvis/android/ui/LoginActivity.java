package com.jarvis.android.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.jarvis.android.JarvisApplication;
import com.jarvis.android.R;
import com.jarvis.android.database.DatabaseManager;
import com.jarvis.android.model.PageFacebook;
import com.jarvis.android.navigationdrawers.NavigationDrawerWithAccountsAndFullHeightActivity;
import com.jarvis.android.utils.GsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Activity of Login screen
 * A simple {@link BaseActivity} sub class
 * <p/>
 */
public class LoginActivity extends BaseActivity {

    private final String TAG = LoginActivity.class.getName();

    private CallbackManager callbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JarvisApplication.setHomeActivity(this);
        setContentView(R.layout.activity_login);
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.jarvis.android", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
        // Facebook callback Manager
        callbackManager = CallbackManager.Factory.create();

        findViewById(R.id.login_facebook_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loginWithFacebook();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void loginWithFacebook() {
        hideLoading();
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        onFacebookSessionOpened(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        showAlert(getString(R.string.error_title), "Canceled!");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        if (exception != null) {
                            showAlert(getString(R.string.error_title), exception.getMessage());
                        }
                    }
                });
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email, manage_pages"));
    }

    private void onFacebookSessionOpened(AccessToken accessToken) {
        if (TextUtils.isEmpty(accessToken.getToken())) {
            showAlert(getString(R.string.error_title), "Access Token is null");
            return;
        } else {
            GraphRequest request = GraphRequest.newGraphPathRequest(
                    accessToken, "me/accounts", new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse graphResponse) {
                            JSONObject result = graphResponse.getJSONObject();
                            JSONArray data = result != null ? result.optJSONArray("data") : null;
                            List<PageFacebook> pageFacebooks = new ArrayList<PageFacebook>();
                            if (data == null) {
                                showAlert(getString(R.string.error_title), "No Pages");
                                return;
                            }
                            for (int i = 0; i < data.length(); i++) {
                                try {
                                    PageFacebook pageFacebook = GsonUtils.getGson().fromJson(data.get(i).toString(), PageFacebook.class);
                                    pageFacebooks.add(pageFacebook);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (!pageFacebooks.isEmpty()) {
                                DatabaseManager.DB.saveOrUpdatePageArray(pageFacebooks);
                                startActivity(new Intent(LoginActivity.this, NavigationDrawerWithAccountsAndFullHeightActivity.class));
                                finish();
                            } else {
                                showAlert(getString(R.string.error_title), "No Pages");
                            }

                        }
                    });

            request.executeAsync();
        }
    }

}