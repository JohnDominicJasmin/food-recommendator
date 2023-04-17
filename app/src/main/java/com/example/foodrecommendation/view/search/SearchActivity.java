package com.example.foodrecommendation.view.search;


import static com.example.foodrecommendation.view.home.HomeActivity.EXTRA_DETAIL;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.foodrecommendation.R;
import com.example.foodrecommendation.adapter.SearchAdapter;
import com.example.foodrecommendation.adapter.SearchResultAdapter;
import com.example.foodrecommendation.model.MealsItem;
import com.example.foodrecommendation.view.detail.DetailActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, com.example.foodrecommendation.view.search.SearchView {

    private SearchAdapter searchAdapter;
    private SearchResultAdapter searchResultAdapter;

    private ListView listViewSuggestions;
    private ListView listViewSearchResult;

    private SearchView searchView;

    private String previousSearchQuery;

    private SearchPresenter presenter;

    private ProgressBar progressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        listViewSuggestions = findViewById(R.id.lv_suggestions);
        listViewSearchResult = findViewById(R.id.lv_searchResults);
        searchView = findViewById(R.id.searchView);
        progressIndicator = findViewById(R.id.progressBarSearch);
        List<String> ingredients = getIngredientSuggestions();

        presenter = new SearchPresenter(this);

        searchAdapter = new SearchAdapter(this, ingredients, (mealName, position) -> {

            if (mealName.equalsIgnoreCase(previousSearchQuery)) {
                return;
            }
            showLoading();
            previousSearchQuery = mealName;
            findFood(mealName);

        });

        listViewSuggestions.setAdapter(searchAdapter);
        searchView.setOnQueryTextListener(SearchActivity.this);

        Intent intent = getIntent();
        String ingredientName = intent.getStringExtra(EXTRA_DETAIL);

        findFood(ingredientName);

    }

    private void findFood(String foodName){
        showLoading();
        presenter = new SearchPresenter(this);
        presenter.getMealByIngredient(SearchActivity.this, foodName, new GetMealByIngredient() {

            @Override
            public void onSuccess(List<MealsItem> meals) {
                if (meals == null) {
                    Toast.makeText(SearchActivity.this, "No Result Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                hideLoading();
                searchResultAdapter = new SearchResultAdapter(SearchActivity.this, meals, (meal, position) -> {
                    Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                    intent.putExtra(EXTRA_DETAIL,meal.toString());
                    startActivity(intent);

                });
                listViewSearchResult.setAdapter(searchResultAdapter);
                listViewSuggestions.setVisibility(View.GONE);
                listViewSearchResult.setVisibility(View.VISIBLE);
                hideLoading();
            }

            @Override
            public void onError(String message) {
                hideLoading();
                Toast.makeText(SearchActivity.this, "Failed to get Ingredient", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (listViewSearchResult.getVisibility() == View.VISIBLE) {
            listViewSearchResult.setVisibility(View.GONE);
        }

        if (newText.isEmpty()) {
            listViewSuggestions.setVisibility(View.GONE);
        }

        if (!newText.isEmpty()) {
            listViewSuggestions.setVisibility(View.VISIBLE);
            searchAdapter.filter(newText);
        }

        return false;
    }

    private ArrayList<String> getIngredientSuggestions() {
        InputStream inputStream = getResources().openRawResource(R.raw.ingredients);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String text = builder.toString();
        String[] items = text.split("\n");

        return new ArrayList<>(Arrays.asList(items));
    }

    @Override
    public void showLoading() {
        progressIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onErrorLoading(String message) {

    }
}