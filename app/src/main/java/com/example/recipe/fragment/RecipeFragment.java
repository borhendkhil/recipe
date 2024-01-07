package com.example.recipe.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe.Addrecipe;
import com.example.recipe.VerticalAdapter;
import com.example.recipe.R;
import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.Recipe;

import java.util.ArrayList;


public class RecipeFragment extends Fragment {
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView;
    DatabaseHandler db;

    VerticalAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        textView= view.findViewById(R.id.t22);
        imageView= view.findViewById(R.id.addrecipelayout);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), Addrecipe.class);
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.recycler);
        db = new DatabaseHandler(getContext());
        ArrayList<Recipe> recipes = new ArrayList<>();
        Cursor cursor = db.getAllRecipes();
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "liste de reccet est vide ", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Recipe recipe = new Recipe();
                recipe.setId(String.valueOf(cursor.getInt(0)));
                recipe.setName(cursor.getString(1));
                recipe.setDescription(cursor.getString(2));
                recipe.setImage(cursor.getBlob(3));
                recipe.setCalory(cursor.getString(4));
                recipe.setCategory(cursor.getString(5));
                recipes.add(recipe);
            }
            Toast.makeText(getContext(), "not vide ", Toast.LENGTH_SHORT).show();
        }
        adapter = new VerticalAdapter(getContext(), recipes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));






        return view;
    }
}