package com.example.recipe.utils;

import android.graphics.Bitmap;

public class Utils {

    public static final String DATABASE_NAME = "recipe.db";
    public static final int DATABASE_VERSION = 1;




    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_PRENOM = "prenom";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    //added role column to add admin
    public static final String COLUMN_ROLE = "role";





    public static final String Table_Recipe = "recipe";
    public static final String COLUMN_ID_RECIPE = "id";
    public static final String COLUMN_NAME_RECIPE = "name";
    public static final String COLUMN_DESCRIPTION_RECIPE = "description";
    public static final String COLUMN_IMAGE_RECIPE = "image";

    public static final String COLUMN_calory_RECIPE = "calory";
    public static final String COLUMN_CATEGORY_RECIPE = "category";




    public static final String Table_Ingredient = "ingredient";
    public static final String COLUMN_ID_INGREDIENT = "id";
    public static final String COLUMN_NAME_INGREDIENT = "name";
    public static final String COLUMN_QUANTITY_INGREDIENT = "quantity";
    public static final String COLUMN_UNIT_INGREDIENT = "unit";




    public static final String Table_Planing = "planing";
    public static final String COLUMN_ID_PLANING = "id";
    public static final String COLUMN_NAME_PLANING = "name";
    public static final String COLUMN_DATE_PLANING = "date";
    public static final String COLUMN_TIME_PLANING = "time";


    public static final String COLUMN_IDRECIPE ="idrecipe" ;
}
