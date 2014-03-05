package com.produktmacher.tagmanagerdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.produktmacher.tagmanagerdemo.R;
import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;
import com.produktmacher.tagmanagerdemo.gtm.interfaces.GTMValueCallback;

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
        gtmSetTitle("Search");

        mButtonSearch   = (Button) findViewById(R.id.search_button_search);
        mEditTextSearch = (EditText) findViewById(R.id.search_edittext_searchfield);
        mTextViewSearchQuestion = (TextView) findViewById(R.id.search_textview_searchquestion);


        // We also need a callback here, since fetching the value might happen asynchronously
        GTMConnector.getInstance(this).getValue(GTM_SEARCH_QUESTION, new GTMValueCallback() {
            @Override
            public void callback(final String value) {

                //We are in a different thread, where we can't change the ui
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextViewSearchQuestion.setText(value);
                    }
                });

            }
        });

        // We also need a callback here, since fetching the value might happen asynchronously
        GTMConnector.getInstance(this).getValue(GTM_SEARCH_BUTTON, new GTMValueCallback() {
            @Override
            public void callback(final String value) {
                //We are in a different thread, where we can't change the ui
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mButtonSearch.setText(value);
                    }
                });
            }
        });


        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent(SearchActivity.this, SearchResultActivity.class);
                result.putExtra(EXTRA_SEARCH_TERM, mEditTextSearch.getText().toString());
                startActivity(result);
            }
        });

    }


}
