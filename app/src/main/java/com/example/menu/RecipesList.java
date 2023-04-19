package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.menu.database.DataBaseHandler;

public class RecipesList extends AppCompatActivity
{
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeslist);

        textView = findViewById(R.id.textView);

        DataBaseHandler dbHandler = new DataBaseHandler(RecipesList.this, null, null, 1);
        textView.setText(dbHandler.loadHandler());

    }
}