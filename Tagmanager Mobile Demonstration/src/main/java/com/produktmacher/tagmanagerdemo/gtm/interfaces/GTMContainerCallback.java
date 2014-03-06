package com.produktmacher.tagmanagerdemo.gtm.interfaces;

import com.google.tagmanager.Container;

/**
 *Created by DieProduktMacher GmbH on March 6th, 2014, www.produktmacher.com
 *
 * This interface offers a method that should be called when the container has successfully been created
 */
public interface GTMContainerCallback {

    public void callback(Container container);


}
