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
 * This is a Singleton Class, with which you can send events and receive values from Google Tag Manager
 */
public class GTMConnector {

    // Your Container ID
    private static final String CONTAINER_ID = "GTM-PLWRDX";
    volatile private Container mContainer;
    private TagManager mTagManager;

    private static GTMConnector instance = new GTMConnector();
    private Context mContext;

    //Singleton
    private GTMConnector() {
    }

    public static GTMConnector getInstance(Context context) {
        instance.mContext = context;
        instance.mTagManager = TagManager.getInstance(context);
        return instance;
    }


    /**
     * Sends an openScreen Event
     * @param screenName
     */
    public void sendScreenOpened(String screenName) {
        DataLayer dataLayer = mTagManager.getDataLayer();
        dataLayer.push(DataLayer.mapOf("event", "openScreen",
                "screenName", screenName));
    }

    /**
     * Sends a liked Event (including the item's name)
     * @param mItem
     */
    public void sendLikedClicked(MyItem mItem) {
        String itemName = mItem.getName();

        DataLayer dataLayer = mTagManager.getDataLayer();
        dataLayer.push(DataLayer.mapOf("event", "liked",
                "likedName", itemName));
    }


    /**
     * Sends a button Clicked Event
     * @param buttonTag Should be set in xml
     */
    public void sendButtonClicked(String buttonTag) {
        DataLayer dataLayer = mTagManager.getDataLayer();

        // This call assumes the container has already been opened, otherwise events
        // pushed to the DataLayer will not fire tags in that container.
        dataLayer.push(DataLayer.mapOf("event", "buttonClicked",        // Event, Name of Open Screen Event.
                "buttonName", buttonTag));   // Additional data layer variables used by the event tag.
    }

    /**
     * Get a value from the Container
     * @param key
     * @param callback is needed, because if there is no saved Container, it first has to be fetched asynchronously
     */
    public void getValue(final String key, final GTMValueCallback callback) {
        // If Container is available already, call the GTMValueCallback with the String value
        // If not, prepare the Container. As soon as it's ready (GTMContainerCallback gets Called): call the GTMValueCallback with the String value
        // We need callbacks since fetching the Container runs asynchronously
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

    /**
     * Prepare the GTM Container
     * @param callback get's called with the container as a param as soon as it's ready
     */
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
                        if (callback != null) {
                            callback.callback(container);
                        }
                    }
                }
        );
    }


    /**
     * Force fetching the container from network
     */
    public void refresh() {
        if (mContainer == null) {
            prepareContainer(new GTMContainerCallback() {
                @Override
                public void callback(Container container) {
                    mContainer.refresh();
                }
            });
        } else {
            mContainer.refresh();
        }
    }
}
