package com.jarvis.android.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.jarvis.android.R;


public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void showLoading() {
        showLoading(null);
    }

    protected void showLoading(@StringRes int resId) {
        showLoading(getString(resId));
    }

    protected void showLoading(final String loadingMessage) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    final View v = findViewById(R.id.loading_view);
                    if (v == null) {
                        return;
                    }
                    TextView message = (TextView) v.findViewById(R.id.loading_title);
                    if (TextUtils.isEmpty(loadingMessage)) {
                        message.setVisibility(View.GONE);
                    } else {
                        message.setVisibility(View.VISIBLE);
                        message.setText(loadingMessage);
                    }
                    if (!isFinishing()) {
                        try {
                            v.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideLoading() {
        final View v = findViewById(R.id.loading_view);
        if (v == null) {
            return;
        }
        if (!isFinishing()) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    v.setVisibility(View.GONE);
                }
            });
        }
    }

    public void showAlert(final String title, final String message) {
        if (!isFinishing()) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    try {
                        new AlertDialog.Builder(BaseActivity.this).setTitle(title)
                                .setMessage(message)
                                .setNegativeButton(android.R.string.ok, null).create()
                                .show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void showAlert(@StringRes final int titleResId, @StringRes final int messageResId) {
        if (!isFinishing()) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    try {
                        new AlertDialog.Builder(BaseActivity.this).setTitle(titleResId)
                                .setMessage(messageResId)
                                .setNegativeButton(android.R.string.ok, null).create()
                                .show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

//    public void showAlert(final String title, final String message, final BaseFragment.AlertListener listener) {
//        runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    new AlertDialog.Builder(BaseActivity.this).setTitle(title)
//                            .setMessage(message)
//                            .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    if (listener != null)
//                                        listener.yesOnClick();
//                                }
//                            }).create()
//                            .show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void showAlert(final String title, final String message, final String yesTitle, final String noTitle, final BaseFragment.AlertListener listener) {
//        runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    new AlertDialog.Builder(BaseActivity.this).setTitle(title)
//                            .setMessage(message)
//                            .setPositiveButton(yesTitle, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    if (listener != null) {
//                                        listener.yesOnClick();
//                                    }
//                                }
//                            })
//                            .setNegativeButton(noTitle, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                }
//                            }).create()
//                            .show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    protected void showKeyboard(View target) {
        if (target == null) {
            return;
        }
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(target,
                InputMethodManager.SHOW_IMPLICIT);
    }

    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
