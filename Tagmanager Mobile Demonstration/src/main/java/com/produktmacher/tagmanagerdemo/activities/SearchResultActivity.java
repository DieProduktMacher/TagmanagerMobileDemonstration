package com.produktmacher.tagmanagerdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.produktmacher.tagmanagerdemo.R;
import com.produktmacher.tagmanagerdemo.adapters.SearchResultAdapter;
import com.produktmacher.tagmanagerdemo.gtm.GTMConnector;
import com.produktmacher.tagmanagerdemo.gtm.interfaces.GTMValueCallback;

public class SearchResultActivity extends GTMBaseActivity {

    public static final String EXTRA_ITEM = "result_item";
    private final String GTM_YOUR_RESULTS = "activity_searchresult_here_are_your_results";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        gtmSetTitle("Search Results");

        TextView mTextViewSearchTerm  = (TextView) findViewById(R.id.searchresult_textview_term);
        final TextView mTextViewYourResults = (TextView) findViewById(R.id.searchresult_textview_yourresults);
        ListView mListView = (ListView) findViewById(R.id.searchresult_listview);

        Bundle extras = getIntent().getExtras();

        String searchTerm = "";
        if (extras != null) {
            searchTerm = extras.getString(SearchActivity.EXTRA_SEARCH_TERM);
            if (!searchTerm.equals("")) {
                mTextViewSearchTerm.setText(searchTerm);
            }
        }

        GTMConnector.getInstance(this).getValue(GTM_YOUR_RESULTS, new GTMValueCallback() {
            @Override
            public void callback(final String value) {
                //We are in a different thread, where we can't change the ui
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextViewYourResults.setText(value);
                    }
                });
            }
        });


        final SearchResultAdapter adapter = new SearchResultAdapter(this, searchTerm);
        mListView.setAdapter(adapter);
        
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent details = new Intent(SearchResultActivity.this, DetailsActivity.class);
                details.putExtra(SearchResultActivity.EXTRA_ITEM, adapter.getItem(position));
                startActivity(details);
            }
        });
    }


}
