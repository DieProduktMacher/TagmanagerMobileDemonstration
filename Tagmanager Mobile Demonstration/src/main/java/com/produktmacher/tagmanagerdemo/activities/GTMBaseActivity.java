package com.produktmacher.tagmanagerdemo.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;


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

}
