package com.example.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recipe.controller.DatabaseHandler;
import com.example.recipe.model.Ingredient;
import com.example.recipe.model.Recipe;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Addrecipe extends AppCompatActivity {


    ImageView back;
    Button button, addrecipe;
    ConstraintLayout container;
    TextInputEditText category;
    TextInputEditText name;
    TextInputEditText description;
    TextInputEditText calories;
    DatabaseHandler db;

    public static final int STORAGE_REQUEST=1;
    ImageView recipeimage;
    Recipe recipe;




    int ingredientCounter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecipe);
        name= findViewById(R.id.nom);
        category= findViewById(R.id.category);
        description= findViewById(R.id.description);
        calories= findViewById(R.id.calory);
        addrecipe= findViewById(R.id.addrecipe);
        recipeimage=findViewById(R.id.recipeimage);
        recipeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        

        db = new DatabaseHandler(this);

        back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        container= findViewById(R.id.container);
        button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewIngredient();

            }
        });

        addrecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipeName = name.getText().toString();
                String recipeCategory = category.getText().toString();
                String recipeDescription = description.getText().toString();
                String recipeCalories = calories.getText().toString();

                recipe.setName(recipeName);
                recipe.setDescription(recipeDescription);
                recipe.setCalory(recipeCalories);
                recipe.setCategory(recipeCategory);

                String recipeid =db.addRecipe(recipe);
                Toast.makeText(Addrecipe.this, "Ajoute avec succees", Toast.LENGTH_SHORT).show();
                finish();


                for (int i = 5; i < container.getChildCount(); i++) {

                    View ingredientFrame = container.getChildAt(i);
                    TextInputEditText editTextIngredient = ingredientFrame.findViewById(R.id.ingredient);
                    TextInputEditText editTextquantity = ingredientFrame.findViewById(R.id.quantity);
                    TextInputEditText editTextunit = ingredientFrame.findViewById(R.id.unit);

                    String ingredientName = editTextIngredient.getText().toString();
                    String ingredientquantity = editTextquantity.getText().toString();
                    String ingredientunit = editTextunit.getText().toString();




                    Ingredient ingredient= new Ingredient(ingredientName,ingredientquantity,ingredientunit,recipeid);
                    db.addIngredient(ingredient);




                }


            }
        });

    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, STORAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == STORAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                byte[] byteArray = bitmapToByteArray(bitmap);
                recipe = new Recipe("", "", byteArray,  "", ""); // Initialize Recipe object
                recipe.setImage(byteArray);



                recipeimage.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addNewIngredient() {
        View ingredientFrame = LayoutInflater.from(Addrecipe.this).inflate(R.layout.addincredent, null);
        ingredientFrame.setId(View.generateViewId());
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );


        if (ingredientCounter == 0) {
            View categoryTextInputLayout = findViewById(R.id.categoryt);
            if (categoryTextInputLayout != null) {
                params.topToBottom = categoryTextInputLayout.getId();
            }
        } else {

            View lastIngredientFrame = container.getChildAt(container.getChildCount() - 1);
            if (lastIngredientFrame != null) {
                params.topToBottom = lastIngredientFrame.getId();
            }
        }
        ingredientFrame.setLayoutParams(params);
        container.addView(ingredientFrame);
        ingredientCounter++;
    }
    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }



}