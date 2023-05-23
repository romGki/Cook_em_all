package com.example.menu.fragmnets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.menu.R;
import com.example.menu.adapters.PreviewRecycleViewAdapter;
import com.example.menu.database.DataBaseHandler;
import com.example.menu.interfaces.RecipeInterface;
import com.example.menu.models.RecipeModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviewFragment extends Fragment implements RecipeInterface
{
    Context thiscontext;
    ArrayList<RecipeModel> recipes;
    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PreviewFragment() {
        // Required empty public constructor
    }

    public static PreviewFragment newInstance(String param1, String param2) {
        PreviewFragment fragment = new PreviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.preview_fragment, container, false);
        thiscontext = container.getContext();
        DataBaseHandler dbHandler = new DataBaseHandler(thiscontext, null, null, 1);
        recipes = dbHandler.loadHandler();

        recyclerView=v.findViewById(R.id.previewRV);

        PreviewRecycleViewAdapter recycleViewAdapter = new PreviewRecycleViewAdapter(thiscontext, recipes, this);
        recyclerView.setAdapter(recycleViewAdapter);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(thiscontext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);

        return v;
    }

    @Override
    public void onItemClicked(int position)
    {
        DataBaseHandler dbHandler = new DataBaseHandler(thiscontext, null, null, 1);
        ArrayList<RecipeModel> checkDetails = dbHandler.loadHandler();

        Intent intent = new Intent(thiscontext, com.example.menu.RecipeDetails.class);

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
}