package com.example.recipe.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;


import androidx.annotation.Nullable;

import com.example.recipe.model.Planing;
import com.example.recipe.model.Recipe;
import com.example.recipe.model.User;
import com.example.recipe.model.Ingredient;
import com.example.recipe.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        //creation tableau BD
        String CREATE_TABLE_USER = "CREATE TABLE " +
//zid col role pour admin
                Utils.TABLE_NAME + "("
                + Utils.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NOM + " TEXT,"
                + Utils.COLUMN_PRENOM + " TEXT,"
                + Utils.COLUMN_EMAIL + " TEXT,"
                + Utils.COLUMN_PASSWORD + " TEXT,"
                + Utils.COLUMN_ROLE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_USER);
        //insertion default admin. NE TOUCHE PAS YAR7AM BOUK
        db.execSQL("INSERT INTO user (nom, prenom, email, password, role) VALUES ('admin', 'admin', 'admin', 'admin', 'Admin')");
        //tab recipes
        String CREATE_TABLE_RECIPE = "CREATE TABLE " + Utils.Table_Recipe + "("
                + Utils.COLUMN_ID_RECIPE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NAME_RECIPE + " TEXT,"
                + Utils.COLUMN_DESCRIPTION_RECIPE + " TEXT,"
                + Utils.COLUMN_IMAGE_RECIPE + " BLOB,"
                + Utils.COLUMN_calory_RECIPE + " TEXT,"
                + Utils.COLUMN_CATEGORY_RECIPE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_RECIPE);
//tab ingredients
        String CREATE_TABLE_INGREDIENT = "CREATE TABLE " + Utils.Table_Ingredient + "("
                + Utils.COLUMN_ID_INGREDIENT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NAME_INGREDIENT + " TEXT,"
                + Utils.COLUMN_QUANTITY_INGREDIENT + " TEXT,"
                + Utils.COLUMN_UNIT_INGREDIENT + " TEXT,"
                + Utils.COLUMN_IDRECIPE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_INGREDIENT);
//tab planning
        String CREATE_TABLE_PLANING = "CREATE TABLE " + Utils.Table_Planing + "("
                + Utils.COLUMN_ID_PLANING + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utils.COLUMN_NAME_PLANING + " TEXT,"
                + Utils.COLUMN_DATE_PLANING + " TEXT,"
                + Utils.COLUMN_TIME_PLANING + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_PLANING);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String DROP_TABLE = String.valueOf("DROP TABLE IF EXISTS");
    db.execSQL(DROP_TABLE, new String[]{Utils.TABLE_NAME});
    db.execSQL(DROP_TABLE, new String[]{Utils.Table_Recipe});
    onCreate(db);

    }
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_NOM, user.getNom());
        values.put(Utils.COLUMN_PRENOM, user.getPrenom());
        values.put(Utils.COLUMN_EMAIL, user.getEmail());
        values.put(Utils.COLUMN_PASSWORD, user.getPassword());
        values.put(Utils.COLUMN_ROLE, user.getRole());
        db.insert(Utils.TABLE_NAME, null, values);

        db.close();
    }
    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.TABLE_NAME,
                new String[]{
                        Utils.COLUMN_ID,
                        Utils.COLUMN_NOM,
                        Utils.COLUMN_PRENOM,
                        Utils.COLUMN_EMAIL,
                        Utils.COLUMN_PASSWORD,
                        Utils.COLUMN_ROLE},
                null, null, null, null, null);
        return cursor;
    }
    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Utils.TABLE_NAME, Utils.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }


    public boolean checkUser(String email, String password){
        String[] columns = {Utils.COLUMN_EMAIL};
        SQLiteDatabase db = getReadableDatabase();
        String selection = Utils.COLUMN_EMAIL + "=?" + " and " + Utils.COLUMN_PASSWORD + "=?" + " and " + Utils.COLUMN_ROLE + "=?" ;
        String[] selectionArgs = {email, password, "Client"};
        Cursor cursor = db.query(Utils.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0)
            return true;
        else
            return false;
    }
    public boolean checkAdmin(String email, String password){
        String[] columns = {Utils.COLUMN_EMAIL};
        SQLiteDatabase db = getReadableDatabase();
        String selection = Utils.COLUMN_EMAIL + "=?" + " and " + Utils.COLUMN_PASSWORD + "=?" + " and " + Utils.COLUMN_ROLE + "=?" ;
        String[] selectionArgs = {email, password, "Admin"};
        Cursor cursor = db.query(Utils.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0)
            return true;
        else
            return false;
    }
    public String addRecipe(Recipe recipe){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_NAME_RECIPE, recipe.getName());
        values.put(Utils.COLUMN_DESCRIPTION_RECIPE, recipe.getDescription());
        values.put(Utils.COLUMN_IMAGE_RECIPE, recipe.getImage());
        values.put(Utils.COLUMN_calory_RECIPE, recipe.getCalory());
        values.put(Utils.COLUMN_CATEGORY_RECIPE, recipe.getCategory());
        db.insert(Utils.Table_Recipe, null, values);
        db.close();

        return recipe.getId();


    }





    

    public Cursor getAllRecipes(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.Table_Recipe,
                new String[]{
                        Utils.COLUMN_ID_RECIPE,
                        Utils.COLUMN_NAME_RECIPE,
                        Utils.COLUMN_DESCRIPTION_RECIPE,
                        Utils.COLUMN_IMAGE_RECIPE,
                        Utils.COLUMN_calory_RECIPE,
                        Utils.COLUMN_CATEGORY_RECIPE},
                null, null, null, null, null);
        return cursor;
    }

    public void addIngredient(Ingredient ingredient){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_NAME_INGREDIENT, ingredient.getName());
        values.put(Utils.COLUMN_QUANTITY_INGREDIENT, ingredient.getQuantity());
        values.put(Utils.COLUMN_UNIT_INGREDIENT, ingredient.getUnit());
        values.put(Utils.COLUMN_ID_RECIPE, ingredient.getRecipeid());
        db.insert(Utils.Table_Ingredient, null, values);
        db.close();
    }

    public void addPlaning(Planing planing){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Utils.COLUMN_NAME_PLANING, planing.getName());
        values.put(Utils.COLUMN_DATE_PLANING, planing.getDate());
        values.put(Utils.COLUMN_TIME_PLANING, planing.getTime());
        db.insert(Utils.Table_Planing, null, values);
        db.close();
    }


    public Cursor getAllIngredientsByRecipeId(String recipeId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.Table_Ingredient,
                new String[]{
                        Utils.COLUMN_ID_INGREDIENT,
                        Utils.COLUMN_NAME_INGREDIENT,
                        Utils.COLUMN_QUANTITY_INGREDIENT,
                        Utils.COLUMN_UNIT_INGREDIENT,
                        Utils.COLUMN_IDRECIPE},
                Utils.COLUMN_IDRECIPE + "=?", new String[]{String.valueOf(recipeId)}, null, null, null, null);
        return cursor;
    }

    public Cursor getAllPlanings(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Utils.Table_Planing,
                new String[]{
                        Utils.COLUMN_ID_PLANING,
                        Utils.COLUMN_NAME_PLANING,
                        Utils.COLUMN_DATE_PLANING,
                        Utils.COLUMN_TIME_PLANING},
                null, null, null, null, null);
        return cursor;
    }







}
