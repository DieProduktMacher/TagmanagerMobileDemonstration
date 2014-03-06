package com.produktmacher.tagmanagerdemo.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;
import com.produktmacher.tagmanagerdemo.gtm.interfaces.GTMValueCallback;


/**
 * This class should be inherited by all tracked Activities
 * GTMBaseActivity helps sending the screenOpened event
 */
public class GTMBaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Set the activity title and push a screenOpened event to the TagManager Container
     * @param title The screens' title
     */
    protected void setTitleAndPushOpened(String title) {
        setTitle(title);
        GTMConnector.getInstance(this).sendScreenOpened(title);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        GTMConnector.getInstance(this).getValue("app_background_color", new GTMValueCallback() {
            @Override
            public void callback(final String value) {
                Log.i("GTM", "COLOR: " + value);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ( value != null && !value.equals("") ) {
                            int color = Color.parseColor(value);
                            if (color != 0) {
                                GTMBaseActivity.this.findViewById(android.R.id.content).setBackgroundColor(color);
                            }
                        }
                    }
                });
            }
        });
    }
}
