package com.example.recipe.model;

import android.graphics.Bitmap;

public class Recipe {
    private String id;
    private String name;
    private byte[] image;
    private String description;
    private String calory;
    private String category;

    public Recipe() {
    }

    public Recipe(String id, String name, String description, byte[] image, String calory, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;

        this.calory = calory;
        this.category = category;
    }

    public Recipe(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public Recipe(String name, String description, byte[] image, String calory, String category) {
        this.name = name;
        this.description = description;
        this.image = image;

        this.calory = calory;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

