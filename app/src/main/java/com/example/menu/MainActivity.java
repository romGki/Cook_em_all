package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.menu.fragmnets.Supermarkets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }

    public void startSupermarketsActivity(View view) {

        Intent intent = new Intent(this, Supermarkets.class);
        startActivity(intent);
    }

    public void startRecipesActivity(View view) {

        Intent intent = new Intent(this, RecipesList.class);
        startActivity(intent);
    }
}