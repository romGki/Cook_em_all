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
import com.example.menu.models.RecipeModel;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>
{

    Context context;
    ArrayList<RecipeModel> recipesModels;

    public RecycleViewAdapter(Context context, ArrayList<RecipeModel> recipesModels)
    {
        this.context = context;
        this.recipesModels = recipesModels;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.itemcards, parent, false);

        return new RecycleViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position)
    {
        holder.name.setText(recipesModels.get(position).getName());
        holder.time.setText(recipesModels.get(position).getTime());
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

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textViewName);
            time = itemView.findViewById(R.id.textViewTime);
        }
    }
}
