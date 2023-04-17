package com.example.foodrecommendation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodrecommendation.view.home.HomeActivity;


public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void back(View view){
        Intent intent = new Intent(About.this, HomeActivity.class);
        startActivity(intent);
    }
}