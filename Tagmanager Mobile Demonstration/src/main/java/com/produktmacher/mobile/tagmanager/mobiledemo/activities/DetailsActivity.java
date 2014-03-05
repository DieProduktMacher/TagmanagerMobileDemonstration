package com.produktmacher.mobile.tagmanager.mobiledemo.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.produktmacher.mobile.tagmanager.mobiledemo.R;
import com.produktmacher.mobile.tagmanager.mobiledemo.models.MyItem;

public class DetailsActivity extends ActionBarActivity {

    private MyItem mItem;
    private TextView mTextViewName;
    private TextView mTextViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mTextViewName = (TextView) findViewById(R.id.details_textview_name);
        mTextViewDetails = (TextView) findViewById(R.id.details_textview_details);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mItem = (MyItem) extras.getSerializable(SearchResultActivity.EXTRA_ITEM);
            mTextViewName.setText(mItem.getName());
            mTextViewDetails.setText(mItem.getDetails());
            DetailsActivity.this.setTitle(mItem.getName());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
