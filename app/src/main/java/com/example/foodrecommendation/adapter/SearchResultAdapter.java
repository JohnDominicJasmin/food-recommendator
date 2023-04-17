package com.example.foodrecommendation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.foodrecommendation.R;
import com.example.foodrecommendation.model.MealsItem;
import com.example.foodrecommendation.view.search.OnClickSearchItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<MealsItem> foodResult;
    private OnClickSearchItem onClickSearchItem;

    public SearchResultAdapter(Context context, List<MealsItem> foodResult, OnClickSearchItem onClickSearchItem) {
        this.mContext = context;
        this.inflater = LayoutInflater.from(mContext);
        this.foodResult = foodResult;
        this.onClickSearchItem = onClickSearchItem;
    }

    public class ViewHolder {
        ImageView imageResult;
        TextView foodName;
    }

    @Override
    public int getCount() {
        return foodResult.size();
    }

    @Override
    public Object getItem(int position) {
        return foodResult.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.search_list_item, null);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageResult = view.findViewById(R.id.iv_foodPicture);
        holder.foodName = view.findViewById(R.id.tv_foodName);
        String meal = foodResult.get(position).getStrMeal();
        String mealThumb = foodResult.get(position).getStrMealThumb();
        Picasso.get().load(mealThumb).into(holder.imageResult);
        holder.foodName.setText(meal);
        view.findViewById(R.id.list_item).setOnClickListener(v -> {
            onClickSearchItem.onClickItem(meal, position);
        });


        return view;
    }
}
