package com.jarvis.android.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.jarvis.android.R;


public abstract class BaseFragment extends Fragment {

    private static String TAG = BaseFragment.class.getName();

    public static final String BUNDLE_IS_TOP_NAVIGATION = "fragment.is.on.top";

    protected boolean firstLoad;

    public BaseFragment() {
        super();
        firstLoad = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

//    public void onNavigationUpdate(NavigationBar navigationBar) {
//        navigationBar.reset();
//        navigationBar.getLeftButton().setImageResource(R.drawable.nav_back);
////        navigationBar.getTitle().setAllCaps(true);
//        navigationBar.getLeftButton().setVisibility(View.VISIBLE);
//        navigationBar.getLeftButton().setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Activity activity = getActivity();
//                if (activity != null && activity instanceof HomeActivity && isAdded()) {
//                    ((HomeActivity) getActivity()).popFragment();
//                }
//            }
//        });
//    }

    public boolean canBack() {
        return true;
    }

    public void recycle() {
    }

    protected void runOnUiThread(Runnable runnable) {
        if (getActivity() == null || !isAdded()) {
            return;
        }
        getActivity().runOnUiThread(runnable);
    }

    /**
     * Show loading in fragment
     */
    protected void showLoading() {
        showLoading(null);
    }

    /**
     * Show loading in fragment
     *
     * @param resId
     */
    protected void showLoading(@StringRes int resId) {
        showLoading(getString(resId));
    }

    /**
     * Show loading in fragment
     *
     * @param loadingMessage
     */
    protected void showLoading(final String loadingMessage) {
        try {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (getView() == null) {
                        return;
                    }
                    final View v = getView().findViewById(R.id.loading_view);
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
                    try {
                        if (isAdded()) {
                            v.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hide loading in fragment
     */
    protected void hideLoading() {
        try {
            final View v;
            if (getView() == null) {
                return;
            }
            v = getView().findViewById(R.id.loading_view);
            if (v == null) {
                return;
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    try {
                        if (isAdded()) {
                            v.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Define content alert in fragment
     *
     * @param title
     * @param message
     * @param listener
     */
    protected void showAlert(final String title, final String message, final AlertListener listener) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    if (getActivity() != null) {
                        new AlertDialog.Builder(getActivity()).setTitle(title)
                                .setMessage(message)
                                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (listener != null)
                                            listener.yesOnClick();
                                    }
                                }).create()
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void showAlert(final String title, final String message, final String yesTitle, final String noTitle, final AlertListener listener) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    if (getActivity() != null) {
                        new AlertDialog.Builder(getActivity()).setTitle(title)
                                .setMessage(message)
                                .setPositiveButton(yesTitle, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (listener != null) {
                                            listener.yesOnClick();
                                        }
                                    }
                                })
                                .setNegativeButton(noTitle, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                }).create()
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Define content alert in fragment
     *
     * @param title
     * @param message
     */
    protected void showAlert(final String title, final String message) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    if (getActivity() != null) {
                        new AlertDialog.Builder(getActivity()).setTitle(title)
                                .setMessage(message)
                                .setNegativeButton(android.R.string.ok, null).create()
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Define content alert in fragment
     *
     * @param titleResId
     * @param messageResId
     */
    protected void showAlert(@StringRes final int titleResId, @StringRes final int messageResId) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    if (getActivity() != null) {
                        new AlertDialog.Builder(getActivity()).setTitle(titleResId)
                                .setMessage(messageResId)
                                .setNegativeButton(android.R.string.ok, null).create()
                                .show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected View findViewById(int id) {
        if (getView() != null) {
            return getView().findViewById(id);
        }
        return null;
    }

    /**
     * Show keyboard in fragment
     *
     * @param target
     */
    protected void showKeyboard(final View target) {
        if (target == null || getActivity() == null) {
            return;
        }
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(target,
                InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * Hide keyboard
     */
    protected void hideKeyboard() {
        if (getActivity() == null) {
            return;
        }
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public AlertListener listener;

    public interface AlertListener {
        void yesOnClick();
    }

}
