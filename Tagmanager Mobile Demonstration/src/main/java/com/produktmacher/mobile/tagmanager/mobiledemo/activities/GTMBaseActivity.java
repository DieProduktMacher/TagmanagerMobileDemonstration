package com.produktmacher.mobile.tagmanager.mobiledemo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.tagmanager.TagManager;
import com.produktmacher.mobile.tagmanager.mobiledemo.gtm.GTMConnector;

/**
 * Created by stefanlanger on 05.03.14.
 */
public class GTMBaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void gtmSetTitle(String title) {
        setTitle(title);
        GTMConnector.getInstance(this).sendScreenOpened(title);
    }

}
