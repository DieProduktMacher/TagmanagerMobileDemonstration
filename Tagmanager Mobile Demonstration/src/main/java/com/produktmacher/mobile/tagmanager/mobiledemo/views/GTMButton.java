package com.produktmacher.mobile.tagmanager.mobiledemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.produktmacher.mobile.tagmanager.mobiledemo.gtm.GTMConnector;

/**
 * Created by stefanlanger on 05.03.14.
 */
public class GTMButton extends Button {

    public GTMButton(Context context) {
        super(context);
    }

    public GTMButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setOnClickListener(final OnClickListener l) {
        OnClickListener newListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                l.onClick(v);
                GTMConnector.sendViewClicked(v);
            }
        };

        super.setOnClickListener(newListener);
    }
}
