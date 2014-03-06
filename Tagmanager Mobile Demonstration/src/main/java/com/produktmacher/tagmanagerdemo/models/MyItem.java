package com.produktmacher.tagmanagerdemo.models;

import java.io.Serializable;

/**
 * Created by DieProduktMacher GmbH on March 6th, 2014, www.produktmacher.com
 *
 * Dummy Item to work with
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
