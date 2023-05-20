package com.example.menu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.menu.R;
import com.example.menu.interfaces.RecipeInterface;
import com.example.menu.models.RecipeModel;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>
{

    Context context;
    ArrayList<RecipeModel> recipesModels;
    public void setFilteredList(ArrayList<RecipeModel> recipesModels)
    {
        this.recipesModels = recipesModels;
        notifyDataSetChanged();
    }
    private final RecipeInterface recipeInterface;

    public RecycleViewAdapter(Context context, ArrayList<RecipeModel> recipesModels, RecipeInterface recipeInterface)
    {
        this.context = context;
        this.recipesModels = recipesModels;
        this.recipeInterface = recipeInterface;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.itemcards, parent, false);

        return new RecycleViewAdapter.ViewHolder(view, recipeInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position)
    {
        holder.name.setText(recipesModels.get(position).getName());
        holder.time.setText(recipesModels.get(position).getTime());
        holder.difficulty.setText(recipesModels.get(position).getDifficulty());
        holder.servings.setText(recipesModels.get(position).getServings());
        holder.imageView.setImageResource(recipesModels.get(position).getImage());



    }

    @Override
    public int getItemCount()
    {
        return recipesModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView name;
        TextView time;
        TextView difficulty;
        TextView servings;

        public ViewHolder(@NonNull View itemView, RecipeInterface recipeInterface)
        {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textViewName);
            time = itemView.findViewById(R.id.textViewTime);
            difficulty = itemView.findViewById(R.id.textViewDifficulty);
            servings = itemView.findViewById(R.id.textViewServings);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(recipeInterface != null)
                    {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION)
                        {
                            recipeInterface.onItemClicked(pos);
                        }
                    }
                }
            });
        }
    }
}
