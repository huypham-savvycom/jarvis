package com.jarvis.android.view;

import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jarvis.android.R;


/**
 * NavigationBar model
 * <p/>
 */
public class NavigationBar {

    private View containerView;

    private ImageView leftButton;
    private TextView title;

    private LinearLayout rightContainer;
    private LinearLayout additionalContainer;


    public NavigationBar(View container) {
        containerView = container;
        leftButton = (ImageView) container.findViewById(R.id.nav_left_button);
        title = (TextView) container.findViewById(R.id.nav_title);
        rightContainer = (LinearLayout) container.findViewById(R.id.nav_right_container);
        additionalContainer = (LinearLayout) container.findViewById(R.id.nav_additional_container);
        if (leftButton == null || title == null || rightContainer == null || additionalContainer == null) {
            throw new NullPointerException("Specified container does not have valid layout elements");
        }
    }


    public void reset() {
        setShow(true);

        leftButton.setVisibility(View.VISIBLE);
        leftButton.setImageDrawable(null);
        title.setText("");
        title.setVisibility(View.VISIBLE);
        rightContainer.removeAllViews();
        additionalContainer.removeAllViews();

    }

    public void setShow(boolean isShow) {
        if (containerView != null) {
            containerView.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    public ImageView getLeftButton() {
        return leftButton;
    }

    public TextView getTitle() {
        return title;
    }

    public void removeRightButtonBadge() {
        if (rightContainer != null) {
            rightContainer.removeAllViews();
        }
    }

    public View addRightButton(@DrawableRes int resId, View.OnClickListener clickHandler) {
        ImageView v = (ImageView) LayoutInflater.from(rightContainer.getContext()).inflate(R.layout.navigation_button, rightContainer, false);
        v.setImageResource(resId);
        v.setOnClickListener(clickHandler);
        rightContainer.addView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return v;
    }

    public View addRightButton(String text, View.OnClickListener clickHandler) {
        TextView v = (TextView) LayoutInflater.from(rightContainer.getContext()).inflate(R.layout.navigation_button_text, rightContainer, false);
        v.setText(text);
        v.setOnClickListener(clickHandler);
        rightContainer.addView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return v;
    }

    public View addCenterButton(@DrawableRes int resId) {
        ImageView v = (ImageView) LayoutInflater.from(additionalContainer.getContext()).inflate(R.layout.navigation_button, additionalContainer, false);
        v.setImageResource(resId);
//        v.setOnClickListener(clickHandler);
        additionalContainer.addView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return v;
    }
}
