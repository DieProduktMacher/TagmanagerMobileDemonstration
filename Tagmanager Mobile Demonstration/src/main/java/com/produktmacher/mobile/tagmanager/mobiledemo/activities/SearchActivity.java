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

public class SearchActivity extends GTMBaseActivity {

    public static final String EXTRA_SEARCH_TERM = "search_term";
    private Button mButtonSearch;
    private TextView mTextViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        gtmSetTitle("Search");

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


}
