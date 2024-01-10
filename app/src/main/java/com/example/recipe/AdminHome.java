package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.User;

import java.util.ArrayList;

public class AdminHome extends AppCompatActivity {
    UserRecycler userRecycler;
    DatabaseHandler db;
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);




        recyclerView = findViewById(R.id.recyclerView);
        db = new DatabaseHandler(this);
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor = db.getAllUsers();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                User user = new User();
                user.setId(cursor.getInt(0));

                user.setNom(cursor.getString(1));
                user.setPrenom(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPassword(cursor.getString(4));
                user.setRole(cursor.getString(5));
                users.add(user);
            }
        }

        if (users.size() > 0) {
            Toast.makeText(this, "data", Toast.LENGTH_SHORT).show();
        userRecycler = new  UserRecycler(this, users);
        recyclerView.setAdapter(userRecycler);
        }


    }
}