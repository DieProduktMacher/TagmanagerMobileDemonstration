package com.produktmacher.mobile.tagmanager.mobiledemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.produktmacher.mobile.tagmanager.mobiledemo.R;

public class SearchActivity extends ActionBarActivity {

    public static final String EXTRA_SEARCH_TERM = "search_term";
    private Button mButtonSearch;
    private TextView mTextViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mButtonSearch = (Button) findViewById(R.id.search_button_search);
        mTextViewSearch = (TextView) findViewById(R.id.search_edittext_searchfield);

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent(SearchActivity.this, SearchResultActivity.class);
                result.putExtra(EXTRA_SEARCH_TERM, mTextViewSearch.getText().toString());
                startActivity(result);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
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
