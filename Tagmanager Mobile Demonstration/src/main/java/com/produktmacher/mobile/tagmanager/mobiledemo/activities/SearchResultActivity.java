package com.produktmacher.mobile.tagmanager.mobiledemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.produktmacher.mobile.tagmanager.mobiledemo.R;
import com.produktmacher.mobile.tagmanager.mobiledemo.adapters.SearchResultAdapter;

public class SearchResultActivity extends ActionBarActivity {

    public static final String EXTRA_ITEM = "result_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        TextView mTextViewSearchTerm = (TextView) findViewById(R.id.searchresult_textview_term);
        ListView mListView = (ListView) findViewById(R.id.searchresult_listview);

        Bundle extras = getIntent().getExtras();

        String searchTerm = "";
        if (extras != null) {
            searchTerm = extras.getString(SearchActivity.EXTRA_SEARCH_TERM);
            if (!searchTerm.equals("")) {
                mTextViewSearchTerm.setText(searchTerm);
            }
        }


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_result, menu);
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
