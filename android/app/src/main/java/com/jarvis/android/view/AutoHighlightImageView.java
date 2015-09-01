package com.jarvis.android.view;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Custom ImageView
 *
 */
public class AutoHighlightImageView extends ImageView {

    public AutoHighlightImageView(Context context) {
        super(context);
    }

    public AutoHighlightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHighlightImageView(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
        if (isEnabled()) {
            if (pressed) {
                setColorFilter(0x55000000, PorterDuff.Mode.SRC_ATOP);
            } else {
                clearColorFilter();
            }
        }
        super.setPressed(pressed);
    }
}
