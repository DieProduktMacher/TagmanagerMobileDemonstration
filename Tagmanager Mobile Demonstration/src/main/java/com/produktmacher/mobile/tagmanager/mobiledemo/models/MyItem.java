package com.produktmacher.mobile.tagmanager.mobiledemo.models;

import java.io.Serializable;

/**
 * Created by stefanlanger on 05.03.14.
 */
public class MyItem implements Serializable{
    private String mName;
    private String mDetails;

    public MyItem(String name, String details) {
        mName = name;
        mDetails = details;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDetails() {
        return mDetails;
    }

    public void setDetails(String details) {
        this.mDetails = details;
    }
}
