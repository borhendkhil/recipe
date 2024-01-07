package com.example.recipe.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipe.R;
import com.example.recipe.RecyclerAdapter;
import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.Planing;
import com.example.recipe.model.Recipe;

import java.util.ArrayList;


public class Home extends Fragment {

    SearchView searchView;
    RecyclerView recyclerView;

    TextView prog;
    RecyclerAdapter adapter;
    DatabaseHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);

        searchView = view.findViewById(R.id.searchView);
        searchView.setQueryHint("chercher une recette");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }

            private void filter(String newText) {

            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);
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
        }
        adapter = new RecyclerAdapter(getContext(), recipes);
        recyclerView.setAdapter(adapter);




        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));




        prog = view.findViewById(R.id.textView4);
        ArrayList<Planing> planings = new ArrayList<>();
        Cursor cursor1 = db.getAllPlanings();
        if (cursor1.getCount() == 0) {

        } else {
            while (cursor1.moveToNext()) {
                Planing planing = new Planing();
                planing.setId(cursor1.getInt(0));
                planing.setName(cursor1.getString(1));
                planing.setDate(cursor1.getString(2));
                planing.setTime(cursor1.getString(3));
                planings.add(planing);

            }
        }
        if (planings.size() == 0) {

        } else {
            prog.setText("vous avez " + planings.size() + " programme");
        }

        return view;
    }
    
   

}