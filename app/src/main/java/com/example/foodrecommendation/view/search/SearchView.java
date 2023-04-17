package com.example.foodrecommendation.view.search;

public interface SearchView {
    void showLoading();
    void hideLoading();
    void onErrorLoading(String message);
}
