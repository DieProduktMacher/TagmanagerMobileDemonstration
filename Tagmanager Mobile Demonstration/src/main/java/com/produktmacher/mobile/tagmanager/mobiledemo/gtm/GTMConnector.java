package com.produktmacher.mobile.tagmanager.mobiledemo.gtm;

import android.view.View;

import com.produktmacher.mobile.tagmanager.mobiledemo.models.MyItem;

/**
 * Created by stefanlanger on 05.03.14.
 */
public class GTMConnector {


    public static void sendViewClicked(View view) {
        GTMConnector.sendViewClicked(view.getTag().toString());
    }

    public static void sendViewClicked(String view) {

    }

    public static void sendViewOpened(String view) {

    }

    public static void sendLikedClicked(View v, MyItem mItem) {
        String tag = v.getTag().toString();
        String itemName = mItem.getName();
    }
}
