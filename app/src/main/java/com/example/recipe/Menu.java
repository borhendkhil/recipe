package com.example.recipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.recipe.fragment.Home;
import com.example.recipe.fragment.Profil;
import com.example.recipe.fragment.RecipeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Menu extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Home homeFragment= new Home();
    Profil profilFragment = new Profil();
    RecipeFragment recipeFragment = new RecipeFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            //bottom nav bar navigation setup
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                    return true;
                }
                if(item.getItemId()==R.id.recipe){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,recipeFragment).commit();
                    return true;
                }
                if(item.getItemId()==R.id.profil){
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,profilFragment).commit();
                    return true;
                }

                return false;
            }
        });

    }
}