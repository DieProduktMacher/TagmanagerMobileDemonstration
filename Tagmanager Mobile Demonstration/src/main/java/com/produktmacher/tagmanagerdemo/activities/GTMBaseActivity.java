package com.produktmacher.tagmanagerdemo.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;


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
