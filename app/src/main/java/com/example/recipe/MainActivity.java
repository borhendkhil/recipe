package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        Intent intent = new Intent(MainActivity.this, Sign_in.class);
                        startActivity(intent);
                        finish();
                    }
                },
                2000);


    }
}