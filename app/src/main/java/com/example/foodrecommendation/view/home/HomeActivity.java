package com.example.foodrecommendation.view.home;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.foodrecommendation.About;
import com.example.foodrecommendation.R;
import com.example.foodrecommendation.Utils;
import com.example.foodrecommendation.adapter.RecyclerViewHomeAdapter;
import com.example.foodrecommendation.adapter.ViewPagerHeaderAdapter;
import com.example.foodrecommendation.model.Categories;
import com.example.foodrecommendation.model.Meals;
import com.example.foodrecommendation.view.category.CategoryActivity;
import com.example.foodrecommendation.view.detail.DetailActivity;
import com.example.foodrecommendation.view.search.SearchActivity;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {


    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";
    private static final int REQUEST_CODE_CAMERA = 1;
    private static final int REQUEST_CODE_GALLERY = 2;
    private static final int CAMERA_LAUNCH_REQUEST_CODE = 11;
    private static final int GALLERY_LAUNCH_REQUEST_CODE = 12;


    @BindView(R.id.viewPagerHeader)
    ViewPager viewPagerMeal;
    @BindView(R.id.recyclerCategory)
    RecyclerView recyclerViewCategory;

    @BindView(R.id.search_btn)
    Button searchButton;

    @BindView(R.id.drawer_layer)
    DrawerLayout drawerLayout;

    private HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = new HomePresenter(this);
        presenter.loadMeals(this);
        presenter.loadFoodCategories(this);
        searchButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(intent);
        });

    }


    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        runOnUiThread(() -> {
            findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
            findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
        });

    }

    private final ActivityResultLauncher<String> selectImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {

                    showLoading();


                    presenter.recognizeFood(uri, this, new OnGetMealCallback() {
                        @Override
                        public void onSuccess(String name) {
                            runOnUiThread(() -> {
                                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                                intent.putExtra(EXTRA_DETAIL, name);
                                startActivity(intent);
                                hideLoading();
                            });

                        }

                        @Override
                        public void onError(String message) {
                            runOnUiThread(() -> {
                                hideLoading();
                                Utils.showDialogMessage(HomeActivity.this, "Error", message);
                            });
                        }
                    });

                }
            });


    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal, this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((v, position) -> {
            TextView mealname = findViewById(R.id.mealName);
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, mealname.getText().toString());
            startActivity(intent);


        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewHomeAdapter homeAdapter = new RecyclerViewHomeAdapter(category, this);
        recyclerViewCategory.setAdapter(homeAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();

        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Title", message);
    }


    public void ClickMenu(View view) {
        opeDrawer(drawerLayout);
    }

    private void opeDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void home(View view) {
        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void About(View view) {
        Intent intent = new Intent(HomeActivity.this, About.class);
        startActivity(intent);
    }


    public void logout(View view) {
        logoutMenu(HomeActivity.this);
    }


    private void logoutMenu(HomeActivity mainActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(0);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        handleCameraResult(requestCode, resultCode);
        handleGalleryResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

//    content://com.android.providers.media.documents/document/image%3A98

    private void handleGalleryResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_LAUNCH_REQUEST_CODE && resultCode == RESULT_OK) {

            Uri uri = data.getData();


            showLoading();
            presenter.recognizeFood(uri, this, new OnGetMealCallback() {
                @Override
                public void onSuccess(String foodName) {
                    runOnUiThread(() -> {

                        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                        intent.putExtra(EXTRA_DETAIL, foodName);
                        startActivity(intent);
                        hideLoading();
                    });

                }

                @Override
                public void onError(String message) {
                    runOnUiThread(() -> {
                        hideLoading();
                        Utils.showDialogMessage(HomeActivity.this, "Error", message);
                    });
                }
            });

        }
    }


    private void handleCameraResult(int requestCode, int resultCode) {
        if (requestCode == CAMERA_LAUNCH_REQUEST_CODE && resultCode == RESULT_OK) {
            File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
            Uri uri = FileProvider.getUriForFile(this, "com.example.foodrecommendation.provider", file);

            showLoading();

            presenter.recognizeFood(uri, this, new OnGetMealCallback() {
                @Override
                public void onSuccess(String foodName) {
                    runOnUiThread(() -> {
                        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                        intent.putExtra(EXTRA_DETAIL, foodName);
                        startActivity(intent);
                        hideLoading();
                    });
                }

                @Override
                public void onError(String message) {
                    runOnUiThread(() -> {
                        hideLoading();
                        Utils.showDialogMessage(HomeActivity.this, "Error", message);
                    });
                }
            });

        }
    }


    private void openCamera() {
        Intent m_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
        Uri uri = FileProvider.getUriForFile(this, "com.example.foodrecommendation.provider", file);
        m_intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(m_intent, CAMERA_LAUNCH_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handleCameraPermission(requestCode, grantResults);
        handleGalleryPermissions(requestCode, grantResults);
    }


    private void handleGalleryPermissions(int requestCode, @NonNull int[] grantResults) {
        if (requestCode != REQUEST_CODE_GALLERY) {
            return;
        }
        if (grantResults.length == 0) {
            return;
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        }

    }

    private void openGallery() {
        selectImageLauncher.launch("image/*");
    }


    private void handleCameraPermission(int requestCode, @NonNull int[] grantResults) {
        if (requestCode != REQUEST_CODE_CAMERA) {
            return;
        }

        if (grantResults.length == 0) {
            return;
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        }


    }

    private Boolean isCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private Boolean isGalleryPermissionGranted() {
        return checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    private void getCameraPermission() {
        if (!isCameraPermissionGranted()) {
            throw new RuntimeException("Camera permission is not granted");
        }
    }

    private void getGalleryPermission() {
        if (!isGalleryPermissionGranted()) {
            throw new RuntimeException("Gallery permission is not granted");
        }
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
    }

    private void requestGalleryPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
    }

    public void ClickGallery(View view) {
        try {
            getGalleryPermission();
            openGallery();
        } catch (RuntimeException e) {
            requestGalleryPermission();
        }
    }

    public void ClickCamera(View view) {
        try {
            getCameraPermission();
            openCamera();
        } catch (RuntimeException e) {
            requestCameraPermission();
        }
    }


}
