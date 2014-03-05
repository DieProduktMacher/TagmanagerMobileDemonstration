package com.produktmacher.tagmanagerdemo.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.produktmacher.tagmanagerdemo.R;
import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;
import com.produktmacher.tagmanagerdemo.models.MyItem;

public class DetailsActivity extends GTMBaseActivity {

    private MyItem mItem;
    private TextView mTextViewName;
    private TextView mTextViewDetails;
    private ImageButton mImageButtonLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        mTextViewName = (TextView) findViewById(R.id.details_textview_name);
        mTextViewDetails = (TextView) findViewById(R.id.details_textview_details);
        mImageButtonLike = (ImageButton) findViewById(R.id.details_button_like);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mItem = (MyItem) extras.getSerializable(SearchResultActivity.EXTRA_ITEM);
            gtmSetTitle(mItem.getName());
            mTextViewName.setText(mItem.getName());
            mTextViewDetails.setText(mItem.getDetails());
            DetailsActivity.this.setTitle(mItem.getName());
        }
        setupListeners();

    }

    private void setupListeners() {
        mImageButtonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GTMConnector.getInstance(DetailsActivity.this).sendLikedClicked(mItem);
            }
        });
    }


}
