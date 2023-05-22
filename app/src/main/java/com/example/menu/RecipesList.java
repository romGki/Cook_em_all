package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.menu.adapters.RecycleViewAdapter;
import com.example.menu.database.DataBaseHandler;
import com.example.menu.interfaces.RecipeInterface;
import com.example.menu.models.RecipeModel;

import java.util.ArrayList;

public class RecipesList extends AppCompatActivity implements RecipeInterface
{
    RecyclerView recyclerView;
    SearchView searchView;
    Button filterButton;
    Button button1;
    Button button2;
    LinearLayout frameLayout;
    ArrayList<RecipeModel> recipes;
    ArrayList<RecipeModel> emptyRecipes;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recipeslist);

        DataBaseHandler dbHandler = new DataBaseHandler(RecipesList.this, null, null, 1);
        recipes = dbHandler.loadHandler();

        recyclerView = findViewById(R.id.recipesRV);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        filterButton=findViewById(R.id.filterButton);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        frameLayout=findViewById(R.id.frameLayout);
        frameLayout.setVisibility(View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                searchFilterList(newText);

                return true;
            }
        });

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, recipes, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

    }

    public ArrayList<RecipeModel> getList() {
        return recipes;
    }

    public void setList(ArrayList<RecipeModel> recipes) {
        this.recipes = recipes;
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

    private void searchFilterList(String newText)
    {
        ArrayList<RecipeModel> seatchfilteredList = new ArrayList<>();
        for(RecipeModel recipe : getList())
        {
            if(recipe.getName().toLowerCase().contains(newText.toLowerCase()))
            {
                seatchfilteredList.add(recipe);
            }
        }

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, seatchfilteredList, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

    }

    public void filterButton(View view)
    {
        int invisible = frameLayout.getVisibility();
        if(invisible==View.VISIBLE)
        {
            frameLayout.setVisibility(View.GONE);
            filterButton.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        }
        else
        {
            frameLayout.setVisibility(View.VISIBLE);
            filterButton.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
    }

    public void filterDiffButton(View view)
    {
        Button b = (Button)view;
        String newText = b.getText().toString();

        ArrayList<RecipeModel> filteredList = new ArrayList<>();
        for(RecipeModel recipe : getList())
        {
            if(recipe.getDifficulty().toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList.add(recipe);
            }
        }

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, filteredList, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

        if(b.getId()==R.id.button1)
        {
            button1.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
            button2.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        }

        if(b.getId()==R.id.button2)
        {
            button2.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
            button1.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        }
    }

    public void filterClearButton(View view)
    {
        DataBaseHandler dbHandler = new DataBaseHandler(RecipesList.this, null, null, 1);
        recipes = dbHandler.loadHandler();

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, recipes, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

        button1.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        button2.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
    }
}