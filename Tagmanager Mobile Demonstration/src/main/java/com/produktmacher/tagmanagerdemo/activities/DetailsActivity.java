package com.produktmacher.tagmanagerdemo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.produktmacher.tagmanagerdemo.R;
import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;
import com.produktmacher.tagmanagerdemo.models.MyItem;

/**
 * Created by DieProduktMacher GmbH on March 6th, 2014, www.produktmacher.com
 *
 * This Activity shows the details to an item
 */
public class DetailsActivity extends GTMBaseActivity {

    private MyItem mItem;
    private TextView mTextViewName;
    private TextView mTextViewDetails;
    private ImageButton mImageButtonLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        mTextViewName       = (TextView) findViewById(R.id.details_textview_name);
        mTextViewDetails    = (TextView) findViewById(R.id.details_textview_details);
        mImageButtonLike    = (ImageButton) findViewById(R.id.details_button_like);

        // Get item from Extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mItem = (MyItem) extras.getSerializable(SearchResultActivity.EXTRA_ITEM);

            //Call the setTitleAndPushOpened function
            setTitleAndPushOpened(mItem.getName());

            // Set details
            mTextViewName.setText(mItem.getName());
            mTextViewDetails.setText(mItem.getDetails());
        }
        setupListeners();
    }

    private void setupListeners() {
        // On Click of the like button
        mImageButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send a liked event
                GTMConnector.getInstance(DetailsActivity.this).sendLikedClicked(mItem);
            }
        });
    }


}
