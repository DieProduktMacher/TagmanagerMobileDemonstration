package com.produktmacher.tagmanagerdemo.gtm;

import android.content.Context;

import com.google.tagmanager.Container;
import com.google.tagmanager.ContainerOpener;
import com.google.tagmanager.DataLayer;
import com.google.tagmanager.TagManager;
import com.produktmacher.tagmanagerdemo.gtm.interfaces.GTMContainerCallback;
import com.produktmacher.tagmanagerdemo.gtm.interfaces.GTMValueCallback;
import com.produktmacher.tagmanagerdemo.models.MyItem;

/**
 * Created by stefanlanger on 05.03.14.
 * This is a Singleton Class, with which you can send events and receive values from Google Tag Manager
 */
public class GTMConnector {

    private static final String CONTAINER_ID = "GTM-PLWRDX";
    volatile private Container mContainer;
    private TagManager mTagManager;

    private static GTMConnector instance = new GTMConnector();
    private Context mContext;

    private GTMConnector() {
    }

    public static GTMConnector getInstance(Context context) {
        instance.mContext = context;
        instance.mTagManager = TagManager.getInstance(context);
        return instance;
    }


    public void sendScreenOpened(String screenName) {
        DataLayer dataLayer = mTagManager.getDataLayer();

        // This call assumes the container has already been opened, otherwise events
        // pushed to the DataLayer will not fire tags in that container.
        dataLayer.push(DataLayer.mapOf("event", "openScreen",        // Event, Name of Open Screen Event.
                "screenName", screenName));  // Name of screen name field, Screen name value.
    }

    public void sendLikedClicked(MyItem mItem) {
        String itemName = mItem.getName();

        DataLayer dataLayer = mTagManager.getDataLayer();

        // This call assumes the container has already been opened, otherwise events
        // pushed to the DataLayer will not fire tags in that container.
        dataLayer.push(DataLayer.mapOf("event", "liked",        // Event, Name of Open Screen Event.
                "likedName", itemName));   // Additional data layer variables used by the event tag.
    }

    public void getValue(final String key, final GTMValueCallback callback) {
        // If Container is available already, call the GTMValueCallback with the String value
        // If not, prepare the Container. As soon as it's ready (GTMContainerCallback gets Called): call the GTMValueCallback with the String value
        // We need callbacks since the Container actions are asynchronous
        if (mContainer != null) {
            String value = mContainer.getString(key);
            callback.callback(value);
        } else {
            prepareContainer(new GTMContainerCallback() {

                @Override
                public void callback(Container container) {
                    String value = container.getString(key);
                    callback.callback(value);
                }
            });
        }
    }

    private void prepareContainer(final GTMContainerCallback callback) {
        // happens asynchronously
        ContainerOpener.openContainer(
                mTagManager,                                    // TagManager instance.
                CONTAINER_ID,                                   // Tag Manager Container ID.
                ContainerOpener.OpenType.PREFER_NON_DEFAULT,    // Prefer not to get the default container, but stale is OK.
                null,                                           // Time to wait for saved container to load (ms). Default is 2000ms.
                new ContainerOpener.Notifier() {                // Called when container loads.
                    @Override
                    public void containerAvailable(Container container) {
                        // Handle assignment in callback to avoid blocking main thread.
                        mContainer = container;
                        callback.callback(container);
                    }
                }
        );
    }
}
