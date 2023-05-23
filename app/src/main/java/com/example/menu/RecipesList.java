package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

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
    Button buttonDiff1;
    Button buttonDiff2;
    Button buttonDiff3;
    Button buttonTime1;
    Button buttonTime2;
    Button buttonTime3;
    Button buttonTime4;
    Button buttonTime5;
    Button buttonTime6;
    Button buttonTime7;
    Button buttonTime8;
    Button buttonTime9;
    Button buttonTime10;
    Button buttonTime11;
    Button buttonTime12;
    Button buttonType1;
    Button buttonType2;
    Button buttonType3;
    Button buttonType4;
    Button buttonType5;
    Button buttonIng1;
    Button buttonIng2;
    Button buttonIng3;
    Button buttonIng4;
    Button buttonIng5;
    Button buttonIng6;
    Button buttonIng7;
    Button buttonIng8;
    Button buttonIng9;
    Button buttonIng10;
    Button buttonIng11;
    Button buttonIng12;
    Button buttonIng13;
    Button buttonIng14;
    Button buttonIng15;
    Button clearButton;
    LinearLayout frameLayout;
    ArrayList<RecipeModel> recipes;


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
        buttonDiff1=findViewById(R.id.buttonDiff1);
        buttonDiff2=findViewById(R.id.buttonDiff2);
        buttonDiff3=findViewById(R.id.buttonDiff3);
        buttonTime1=findViewById(R.id.buttonTime1);
        buttonTime2=findViewById(R.id.buttonTime2);
        buttonTime3=findViewById(R.id.buttonTime3);
        buttonTime4=findViewById(R.id.buttonTime4);
        buttonTime5=findViewById(R.id.buttonTime5);
        buttonTime6=findViewById(R.id.buttonTime6);
        buttonTime7=findViewById(R.id.buttonTime7);
        buttonTime8=findViewById(R.id.buttonTime8);
        buttonTime9=findViewById(R.id.buttonTime9);
        buttonTime10=findViewById(R.id.buttonTime10);
        buttonTime11=findViewById(R.id.buttonTime11);
        buttonTime12=findViewById(R.id.buttonTime12);
        buttonType1=findViewById(R.id.buttonType1);
        buttonType2=findViewById(R.id.buttonType2);
        buttonType3=findViewById(R.id.buttonType3);
        buttonType4=findViewById(R.id.buttonType4);
        buttonType5=findViewById(R.id.buttonType5);
        buttonIng1=findViewById(R.id.buttonIng1);
        buttonIng2=findViewById(R.id.buttonIng2);
        buttonIng3=findViewById(R.id.buttonIng3);
        buttonIng4=findViewById(R.id.buttonIng4);
        buttonIng5=findViewById(R.id.buttonIng5);
        buttonIng6=findViewById(R.id.buttonIng6);
        buttonIng7=findViewById(R.id.buttonIng7);
        buttonIng8=findViewById(R.id.buttonIng8);
        buttonIng9=findViewById(R.id.buttonIng9);
        buttonIng10=findViewById(R.id.buttonIng10);
        buttonIng11=findViewById(R.id.buttonIng11);
        buttonIng12=findViewById(R.id.buttonIng12);
        buttonIng13=findViewById(R.id.buttonIng13);
        buttonIng14=findViewById(R.id.buttonIng14);
        buttonIng15=findViewById(R.id.buttonIng15);
        clearButton=findViewById(R.id.clearButton);
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
        ArrayList<RecipeModel> checkDetails = getList();

        Intent intent = new Intent(RecipesList.this, com.example.menu.RecipeDetails.class);

        intent.putExtra("name", checkDetails.get(position).getName());
        intent.putExtra("intro", checkDetails.get(position).getIntro());
        intent.putExtra("time", checkDetails.get(position).getTime());
        intent.putExtra("ingredients", checkDetails.get(position).getIngredients());
        intent.putExtra("instructions", checkDetails.get(position).getInstructions());
        intent.putExtra("image", checkDetails.get(position).getImage());
        intent.putExtra("difficulty", checkDetails.get(position).getDifficulty());
        intent.putExtra("servings", checkDetails.get(position).getServings());

        startActivity(intent);
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

    private void searchFilterList(String newText)
    {
        ArrayList<RecipeModel> filteredList = new ArrayList<>();
        for(RecipeModel recipe : getList())
        {
            if(recipe.getName().toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList.add(recipe);
            }
        }

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, filteredList, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

        setList(filteredList);

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

        if(b.getId()==R.id.buttonDiff1)
        {
            buttonDiff1.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonDiff2)
        {
            buttonDiff2.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonDiff3)
        {
            buttonDiff3.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }

        setList(filteredList);
    }

    public void filterTypeButton(View view)
    {
        Button b = (Button)view;
        String newText = b.getText().toString();

        ArrayList<RecipeModel> filteredList = new ArrayList<>();
        for(RecipeModel recipe : getList())
        {
            if(recipe.getType().toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList.add(recipe);
            }
        }

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, filteredList, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

        if(b.getId()==R.id.buttonType1)
        {
            buttonType1.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonType2)
        {
            buttonType2.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonType3)
        {
            buttonType3.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonType4)
        {
            buttonType4.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonType5)
        {
            buttonType5.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }

        setList(filteredList);
    }

    public void filterIngButton(View view)
    {
        Button b = (Button)view;
        String newText = b.getText().toString();

        ArrayList<RecipeModel> filteredList = new ArrayList<>();
        for(RecipeModel recipe : getList())
        {
            if(recipe.getMainIngredient().toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList.add(recipe);
            }
        }

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, filteredList, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

        if(b.getId()==R.id.buttonIng1)
        {
            buttonIng1.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng2)
        {
            buttonIng2.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng3)
        {
            buttonIng3.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng4)
        {
            buttonIng4.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng5)
        {
            buttonIng5.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng6)
        {
            buttonIng6.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng7)
        {
            buttonIng7.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng8)
        {
            buttonIng8.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng9)
        {
            buttonIng9.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng10)
        {
            buttonIng10.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng11)
        {
            buttonIng11.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng12)
        {
            buttonIng12.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng13)
        {
            buttonIng13.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng14)
        {
            buttonIng14.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonIng15)
        {
            buttonIng15.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }

        setList(filteredList);
    }

    public void filterTimeButton(View view)
    {
        Button b = (Button)view;
        String newText = b.getText().toString();

        ArrayList<RecipeModel> filteredList = new ArrayList<>();
        for(RecipeModel recipe : getList())
        {
            if(recipe.getTime().toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList.add(recipe);
            }
        }

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, filteredList, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

        if(b.getId()==R.id.buttonTime1)
        {
            buttonTime1.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime2)
        {
            buttonTime2.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime3)
        {
            buttonTime3.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime4)
        {
            buttonTime4.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime5)
        {
            buttonTime5.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime6)
        {
            buttonTime6.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime7)
        {
            buttonTime7.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime8)
        {
            buttonTime8.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime9)
        {
            buttonTime9.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime10)
        {
            buttonTime10.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime11)
        {
            buttonTime11.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }
        if(b.getId()==R.id.buttonTime12)
        {
            buttonTime12.setBackgroundTintList(this.getColorStateList(R.color.button_color_change));
        }

        setList(filteredList);
    }

    public void filterClearButton(View view)
    {
        DataBaseHandler dbHandler = new DataBaseHandler(RecipesList.this, null, null, 1);
        recipes = dbHandler.loadHandler();

        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(RecipesList.this, recipes, this);
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecipesList.this));

        buttonDiff1.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonDiff2.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime1.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime2.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime3.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime4.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime5.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime6.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime7.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime8.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime9.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime10.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime11.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonTime12.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonType1.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonType2.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonType3.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonType4.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonType5.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng1.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng2.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng3.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng4.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng5.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng6.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng7.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng8.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng9.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng10.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng11.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng12.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng13.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng14.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonIng15.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));
        buttonDiff3.setBackgroundTintList(this.getColorStateList(R.color.button_color_normal));


        setList(recipes);
    }
}