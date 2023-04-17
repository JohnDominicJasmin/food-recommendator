package com.example.foodrecommendation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.foodrecommendation.R;
import com.example.foodrecommendation.view.search.OnClickSearchItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



public class SearchAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<String> meals = null;
    private ArrayList<String> arraylist;
    private OnClickSearchItem onClickSearchItem;



    public SearchAdapter(Context context, List<String> meals, OnClickSearchItem onClickSearchItem) {
        mContext = context;
        this.meals = meals;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(meals);
        this.onClickSearchItem = onClickSearchItem;
    }

    public class ViewHolder {
        TextView name;
    }


    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public String getItem(int position) {
        return meals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);

            // Locate the TextViews in listview_item.xml
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name = view.findViewById(R.id.nameLabel);
        holder.name.setText(meals.get(position));
        view.findViewById(R.id.list_item).setOnClickListener(v -> onClickSearchItem.onClickItem(meals.get(position), position));
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        meals.clear();
        if (charText.length() == 0) {
            meals.addAll(arraylist);
        } else {
            for (String wp : arraylist) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    meals.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}