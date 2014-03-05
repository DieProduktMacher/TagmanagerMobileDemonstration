package com.produktmacher.tagmanagerdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


/**
 * Created by stefanlanger on 05.03.14.
 */
public class GTMImageButton extends ImageButton {

    public GTMImageButton(Context context) {
        super(context);
    }

    public GTMImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnClickListener(final OnClickListener l) {
        OnClickListener newListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                l.onClick(v);
            }
        };

        super.setOnClickListener(newListener);
    }
}
