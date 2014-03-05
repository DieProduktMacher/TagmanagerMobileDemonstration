package com.produktmacher.tagmanagerdemo.gtm.interfaces;

import com.google.tagmanager.Container;

/**
 * This interface offers a method that should be called when the container has successfully been created
 */
public interface GTMContainerCallback {

    public void callback(Container container);


}
