package com.example.foodrecommendation.view.detail;



import static com.example.foodrecommendation.view.home.HomeActivity.EXTRA_DETAIL;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;


import com.example.foodrecommendation.R;
import com.example.foodrecommendation.Utils;
import com.example.foodrecommendation.model.Meals;
import com.example.foodrecommendation.model.TotalNutrients;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity  implements DetailView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.mealThumb)
    ImageView mealThumb;
    
    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.instructions)
    TextView instructions;

    @BindView(R.id.ingredient)
    TextView ingredients;
    
    @BindView(R.id.measure)
    TextView measures;


    @BindView(R.id.nutrients)
    TextView tv_nutrients;

    @BindView(R.id.nutrient_measures)
    TextView tv_nutrient_measures;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        setupActionBar();

        Intent intent = getIntent();
        String mealName = intent.getStringExtra(EXTRA_DETAIL);

        DetailPresenter presenter = new DetailPresenter(this);
        presenter.getMealByName(this, mealName);
        presenter.getFoodNutrients(this, mealName);
        



        
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorWhite),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showloading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideloading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void setMeal(Meals.Meal meal) {
        Picasso.get().load(meal.getStrMealThumb()).into(mealThumb);
        collapsingToolbarLayout.setTitle(meal.getStrMeal());
        category.setText(meal.getStrCategory());

        instructions.setText(meal.getStrInstructions());
        setupActionBar();

        if (!meal.getStrIngredient1().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient1());
        }
        if (!meal.getStrIngredient2().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient2());
        }
        if (!meal.getStrIngredient3().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient3());
        }
        if (!meal.getStrIngredient4().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient4());
        }
        if (!meal.getStrIngredient5().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient5());
        }
        if (!meal.getStrIngredient6().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient6());
        }
        if (!meal.getStrIngredient7().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient7());
        }
        if (!meal.getStrIngredient8().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient8());
        }
        if (!meal.getStrIngredient9().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient9());
        }
        if (!meal.getStrIngredient10().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient10());
        }
        if (!meal.getStrIngredient11().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient11());
        }
        if (!meal.getStrIngredient12().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient12());
        }
        if (!meal.getStrIngredient13().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient13());
        }
        if (!meal.getStrIngredient14().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient14());
        }
        if (!meal.getStrIngredient15().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient15());
        }
        if (!meal.getStrIngredient16().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient16());
        }
        if (!meal.getStrIngredient17().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient17());
        }
        if (!meal.getStrIngredient18().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient18());
        }
        if (!meal.getStrIngredient19().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient19());
        }
        if (!meal.getStrIngredient20().isEmpty()){
            ingredients.append("\n \u2022" + meal.getStrIngredient20());

        }
        if (!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure1());
        }
        if (!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure2());
        }
        if (!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure3());
        }
        if (!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure4());
        }
        if (!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure5());
        }
        if (!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure6());
        }
        if (!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure7());
        }
        if (!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure8());
        }
        if (!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure9());
        }
        if (!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure10());
        }
        if (!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure11());
        }
        if (!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure12());
        }
        if (!meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure13());
        }
        if (!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure14());
        }
        if (!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure15());
        }
        if (!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure16());
        }
        if (!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure17());
        }
        if (!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure18());
        }
        if (!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure19());
        }
        if (!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0))){
            measures.append("\n : " + meal.getStrMeasure20());
        }




    }

    @Override
    public void setFoodNutrients(TotalNutrients nutrients) {


        DecimalFormat df = new DecimalFormat("#.##");


        tv_nutrients.append("\n \u2022" + nutrients.getENERCKCAL().getLabel());
        tv_nutrients.append("\n \u2022" + nutrients.getFAT().getLabel());
        tv_nutrients.append("\n \u2022" + nutrients.getFASAT().getLabel());

        tv_nutrients.append("\n \u2022" + nutrients.getVITB6A().getLabel());
        tv_nutrients.append("\n \u2022" + nutrients.getPROCNT().getLabel());
        tv_nutrients.append("\n \u2022" + nutrients.getCA().getLabel());

        tv_nutrients.append("\n \u2022" + nutrients.getZN().getLabel());
        tv_nutrients.append("\n \u2022" + nutrients.getFE().getLabel());
        tv_nutrients.append("\n \u2022" + nutrients.getP().getLabel());

        tv_nutrients.append("\n \u2022" + nutrients.getVITARAE().getLabel());
        tv_nutrients.append("\n \u2022" + nutrients.getVITD().getLabel());
        tv_nutrients.append("\n \u2022" + nutrients.getVITC().getLabel());


        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getENERCKCAL().getQuantity())) + " " + nutrients.getENERCKCAL().getUnit());
        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getFAT().getQuantity())) + " " + nutrients.getFAT().getUnit());
        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getFASAT().getQuantity())) + " " + nutrients.getFASAT().getUnit());

        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getVITB6A().getQuantity())) + " " + nutrients.getVITB6A().getUnit());
        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getPROCNT().getQuantity())) + " " + nutrients.getPROCNT().getUnit());
        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getCA().getQuantity())) + " " + nutrients.getCA().getUnit());

        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getZN().getQuantity())) + " " + nutrients.getZN().getUnit());
        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getFE().getQuantity())) + " " + nutrients.getFE().getUnit());
        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getP().getQuantity())) + " " + nutrients.getP().getUnit());

        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getVITARAE().getQuantity())) + " " + nutrients.getVITARAE().getUnit());
        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getVITD().getQuantity())) + " " + nutrients.getVITD().getUnit());
        tv_nutrient_measures.append("\n : " + Double.parseDouble(df.format(nutrients.getVITC().getQuantity())) + " " + nutrients.getVITC().getUnit());

    }

    @Override
    public void onErrorloading(String message) {
        Utils.showDialogMessage(this,"Error", message);

    }
}
