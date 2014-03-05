package com.produktmacher.mobile.tagmanager.mobiledemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.produktmacher.mobile.tagmanager.mobiledemo.models.MyItem;

import java.util.ArrayList;

/**
 * Created by stefanlanger on 05.03.14.
 */
public class SearchResultAdapter extends BaseAdapter {
    private Context mContext;
    private String mSearchTerm;
    private String[] loremArray = {"Lorem", "ipsum", "dolor", "sit", "amet"};
    private String lorem = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod\n" +
            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,\n" +
            "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo\n" +
            "consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse\n" +
            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non\n" +
            "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    private ArrayList<MyItem> myItems;

    public SearchResultAdapter(Context context, String searchTerm) {
        mContext = context;
        mSearchTerm = searchTerm;
        myItems = new ArrayList<MyItem>();

        for (int i = 0; i < 5; i++) {
            myItems.add(new MyItem(loremArray[i], lorem));
        }
    }

    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public MyItem getItem(int position) {
        return myItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null, false);

        TextView titleTextView = (TextView) view.findViewById(android.R.id.text1);
        titleTextView.setText(getItem(position).getName());

        return view;
    }
}
