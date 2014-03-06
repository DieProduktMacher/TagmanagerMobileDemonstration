package com.produktmacher.tagmanagerdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.produktmacher.tagmanagerdemo.R;
import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;
import com.produktmacher.tagmanagerdemo.gtm.interfaces.GTMValueCallback;

/**
 * Created by DieProduktMacher GmbH on March 6th, 2014, www.produktmacher.com
 *
 * This Activity is the Start Activity of this app and shows a search field
 */
public class SearchActivity extends GTMBaseActivity {

    public static final String EXTRA_SEARCH_TERM    = "search_term";
    private final String GTM_SEARCH_QUESTION        = "activity_search_searchquestion";
    private final String GTM_SEARCH_BUTTON          = "activity_search_buttontext";

    private Button mButtonSearch;
    private EditText mEditTextSearch;
    private TextView mTextViewSearchQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // If the app gets started savedInstanceState is null, that's when we wan't to refresh the Container (otherwise it will update automatically after 12 hours)
        if (savedInstanceState == null) {
            GTMConnector.getInstance(this).refresh();
        }

        setTitleAndPushOpened("Search");

        mButtonSearch           = (Button) findViewById(R.id.search_button_search);
        mEditTextSearch         = (EditText) findViewById(R.id.search_edittext_searchfield);
        mTextViewSearchQuestion = (TextView) findViewById(R.id.search_textview_searchquestion);


        // We need a callback to fetch a value from the Container, since fetching it from GTM might happen in another thread
        GTMConnector.getInstance(this).getValue(GTM_SEARCH_QUESTION, new GTMValueCallback() {
            @Override
            public void callback(final String value) {

                // Since this callback might be run in a different thread, we have to assure, that UI actions are run in the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextViewSearchQuestion.setText(value);
                    }
                });

            }
        });

        // We need a callback to fetch a value from the Container, since fetching it from GTM might happen in another thread
        GTMConnector.getInstance(this).getValue(GTM_SEARCH_BUTTON, new GTMValueCallback() {
            @Override
            public void callback(final String value) {
                // Since this callback might be run in a different thread, we have to assure, that UI actions are run in the UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mButtonSearch.setText(value);
                    }
                });
            }
        });


        // Open SearchResultActivity with the search term as an extra
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent(SearchActivity.this, SearchResultActivity.class);
                result.putExtra(EXTRA_SEARCH_TERM, mEditTextSearch.getText().toString());
                startActivity(result);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
