package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.menu.database.DataBaseHandler;
import com.example.menu.models.RecipeModel;

import java.util.ArrayList;

public class TestChamber extends AppCompatActivity
{
    Button buttontest;
    TextView textViewtest;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chamber);

        DataBaseHandler dbHandler = new DataBaseHandler(TestChamber.this, null, null, 1);
        ArrayList<RecipeModel> recipes = dbHandler.loadHandler();

        buttontest = findViewById(R.id.recipesButton);
        textViewtest = findViewById(R.id.textViewPrint);

        buttontest.setOnClickListener(v -> onButtonClick());

        textViewtest.setText(recipes.get(0).getName());

    }

    public void onButtonClick()
    {
        Intent intent = new Intent(TestChamber.this, com.example.menu.RecipesList.class);
        startActivity(intent);
    }
}