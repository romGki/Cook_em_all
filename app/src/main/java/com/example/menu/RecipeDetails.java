package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.menu.adapters.RecycleViewAdapter;
import com.example.menu.database.DataBaseHandler;
import com.example.menu.interfaces.RecipeInterface;
import com.example.menu.models.RecipeModel;

import java.util.ArrayList;

public class RecipeDetails extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recipe_details);

        String name = getIntent().getStringExtra("name");
        String intro = getIntent().getStringExtra("intro");
        String time = getIntent().getStringExtra("time");
        String ingredients = getIntent().getStringExtra("ingredients");
        String instructions = getIntent().getStringExtra("instructions");
        String difficulty = getIntent().getStringExtra("difficulty");
        String servings = getIntent().getStringExtra("servings");
        int image = getIntent().getIntExtra("image", 0);

        ImageView imageView = findViewById(R.id.recipeImageView);
        imageView.setImageResource(image);
        TextView nameTV = findViewById(R.id.textViewDispName);
        nameTV.setText(name);
        TextView introTV = findViewById(R.id.textViewDispIntro);
        introTV.setText(intro);
        TextView timeTV = findViewById(R.id.textViewDispTime);
        timeTV.setText(time);
        TextView difficultyTV = findViewById(R.id.textViewDispDifficulty);
        difficultyTV.setText(difficulty);
        TextView servingsTV = findViewById(R.id.textViewDispServings);
        servingsTV.setText(servings);
        TextView ingredientsTV = findViewById(R.id.textViewDispIngredients);
        ingredientsTV.setText(ingredients);
        TextView instructionsTV = findViewById(R.id.textViewDispSteps);
        instructionsTV.setText(instructions);



    }

}