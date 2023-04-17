package com.example.foodrecommendation;

import android.app.AlertDialog;
import android.content.Context;

import com.example.foodrecommendation.api.EdamamApi;
import com.example.foodrecommendation.api.FoodApi;
import com.example.foodrecommendation.api.FoodClient;


public class Utils {

    public static FoodApi getFoodApi(Context context) {
        return new FoodClient().getFoodClient(context).create(FoodApi.class);
    }

    public static EdamamApi getEdamamApi(Context context) {
        return new FoodClient().getFoodNutrients(context).create(EdamamApi.class);
    }



    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();

        return alertDialog;
    }
}
