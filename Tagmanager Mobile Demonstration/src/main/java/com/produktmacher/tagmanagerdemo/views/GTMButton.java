package com.produktmacher.tagmanagerdemo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;


/**
 * Created by stefanlanger on 05.03.14.
 */
public class GTMButton extends Button {

    private Context mContext;

    public GTMButton(Context context) {
        super(context);
        mContext = context;
    }

    public GTMButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    public void setOnClickListener(final OnClickListener l) {
        OnClickListener newListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                l.onClick(v);
                GTMConnector.getInstance(mContext).sendButtonClicked(v.getTag().toString());
            }
        };
        super.setOnClickListener(newListener);
    }
}
