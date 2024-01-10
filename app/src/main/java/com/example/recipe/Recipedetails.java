package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.Ingredient;

import java.util.ArrayList;

public class Recipedetails extends AppCompatActivity {
    TextView name, calory, category, description, ing;
    ImageView image;
    DatabaseHandler db;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetails);

        Intent intent = getIntent();
        String recipeName = intent.getStringExtra("recipeName");
        String recipeCalory = intent.getStringExtra("recipeCalory");
        String i = intent.getStringExtra("id");
        String recipecategory = intent.getStringExtra("category");
        String recipedescription = intent.getStringExtra("discription");
        id = Integer.parseInt(i);


        name = findViewById(R.id.nom);
        calory = findViewById(R.id.calory);
        category = findViewById(R.id.cat);
        description = findViewById(R.id.des);
        image = findViewById(R.id.image);
        name.setText(recipeName);
        calory.setText(recipeCalory);
        category.setText(recipecategory);
        description.setText(recipedescription);

        byte[] recipeImageBytes = intent.getByteArrayExtra("recipeImage");
        if (recipeImageBytes != null && recipeImageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(recipeImageBytes, 0, recipeImageBytes.length);
            image.setImageBitmap(bitmap);
        } else {
            image.setImageResource(R.drawable.logo);
        }
//        ing.findViewById(R.id.ingredent);
//        db = new DatabaseHandler(this);
//        ArrayList<Ingredient> ingredients = new ArrayList<>();
//        Cursor cursor = db.getAllIngredientsByRecipeId(i);
//        if (cursor.getCount() == 0) {
////            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
//        } else {
//            while (cursor.moveToNext()) {
//                Ingredient ingredient = new Ingredient();
//                ingredient.setId(cursor.getInt(0));
//                ingredient.setName(cursor.getString(1));
//                ingredient.setQuantity(cursor.getString(2));
//                ingredient.setRecipeid(i);
//                ingredients.add(ingredient);
//            }
//        }
//        if (ingredients.size() > 0) {
//            for (int j = 0; j < ingredients.size(); j++) {
//                ing.append(ingredients.get(j).getName() + " " + ingredients.get(j).getQuantity() + "\n");
//            }
//        }


        









        

        
    }
}