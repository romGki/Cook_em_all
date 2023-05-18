package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.menu.adapters.RecycleViewAdapter;
import com.example.menu.database.DataBaseHandler;
import com.example.menu.interfaces.RecipeInterface;
import com.example.menu.models.RecipeModel;

import java.util.ArrayList;

public class RecipesList extends AppCompatActivity implements RecipeInterface
{
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recipeslist);

        DataBaseHandler dbHandler = new DataBaseHandler(RecipesList.this, null, null, 1);
        ArrayList<RecipeModel> recipes = dbHandler.loadHandler();

        recyclerView = findViewById(R.id.recipesRV);

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, recipes, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

    }

    @Override
    public void onItemClicked(int position)
    {
        DataBaseHandler dbHandler = new DataBaseHandler(RecipesList.this, null, null, 1);
        ArrayList<RecipeModel> recipes = dbHandler.loadHandler();

        Intent intent = new Intent(RecipesList.this, com.example.menu.RecipeDetails.class);

        intent.putExtra("name", recipes.get(position).getName());
        intent.putExtra("intro", recipes.get(position).getIntro());
        intent.putExtra("time", recipes.get(position).getTime());
        intent.putExtra("ingredients", recipes.get(position).getIngredients());
        intent.putExtra("instructions", recipes.get(position).getInstructions());
        intent.putExtra("image", recipes.get(position).getImage());
        intent.putExtra("difficulty", recipes.get(position).getDifficulty());
        intent.putExtra("servings", recipes.get(position).getServings());

        startActivity(intent);

    }
}