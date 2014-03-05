package com.produktmacher.mobile.tagmanager.mobiledemo.gtm;

import android.view.View;

/**
 * Created by stefanlanger on 05.03.14.
 */
public class GTMConnector {


    public static void sendViewClicked(View view) {
        GTMConnector.sendViewClicked(view.getTag().toString());
    }

    public static void sendViewClicked(String view) {

    }

    public static void sendViewOpened(View view) {

    }

    public static void sendViewOpened(String view) {

    }
}
