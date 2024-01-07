package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Recipedetails extends AppCompatActivity {
    TextView name, calory, category, description;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetails);

        Intent intent = getIntent();
        String recipeName = intent.getStringExtra("recipeName");
        int recipeCalory = intent.getIntExtra("recipeCalory", 0);
        int id = intent.getIntExtra("id", 0);
        String recipecategory = intent.getStringExtra("category");
        String recipedescription = intent.getStringExtra("discription");


        name = findViewById(R.id.nom);
        calory = findViewById(R.id.calory);
        category = findViewById(R.id.cat);
        description = findViewById(R.id.des);
        image = findViewById(R.id.image);

        name.setText(recipeName);
        calory.setText(String.valueOf(recipeCalory));
        category.setText(recipecategory);
        description.setText(recipedescription);

        byte[] recipeImageBytes = intent.getByteArrayExtra("recipeImage");
        if (recipeImageBytes != null && recipeImageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(recipeImageBytes, 0, recipeImageBytes.length);
            image.setImageBitmap(bitmap);
        } else {
            image.setImageResource(R.drawable.logo);
        }






        

        
    }
}