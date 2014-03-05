package com.produktmacher.mobile.tagmanager.mobiledemo.gtm;

import android.content.Context;
import android.view.View;

import com.google.tagmanager.Container;
import com.google.tagmanager.ContainerOpener;
import com.google.tagmanager.TagManager;
import com.produktmacher.mobile.tagmanager.mobiledemo.gtm.interfaces.GTMContainerCallback;
import com.produktmacher.mobile.tagmanager.mobiledemo.gtm.interfaces.GTMValueCallback;
import com.produktmacher.mobile.tagmanager.mobiledemo.models.MyItem;

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
        return instance;
    }


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

    private TagManager getTagManager() {
        if (mTagManager == null) {
            mTagManager = TagManager.getInstance(mContext);
        }
        return mTagManager;
    }

    private void prepareContainer(final GTMContainerCallback callback) {
        TagManager tagManager = getTagManager();

        // happens asynchronously
        ContainerOpener.openContainer(
                tagManager,                                    // TagManager instance.
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
