package com.csce4623.rynolan.urush.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.csce4623.rynolan.urush.R;
import com.csce4623.rynolan.urush.models.NewsItem;

import java.util.ArrayList;

public class NewsFeedArrayAdapter extends ArrayAdapter<NewsItem> {
    public NewsFeedArrayAdapter(Context context, ArrayList<NewsItem> values) {
        super(context, -1, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View fragmentView = layoutInflater.inflate(R.layout.list_view_item, parent, false);

        TextView header = (TextView) fragmentView.findViewById(R.id.tvCardHeader);
        TextView date = (TextView) fragmentView.findViewById(R.id.tvCardDate);
        TextView content = (TextView) fragmentView.findViewById(R.id.tvCardDescription);

        NewsItem item = getItem(position);
        header.setText(item.getTitle());
        date.setText(item.getDate());
        content.setText(item.getDescription());

        return fragmentView;
    }
}
