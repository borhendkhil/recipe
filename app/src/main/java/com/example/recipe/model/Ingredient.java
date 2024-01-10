package com.example.recipe.model;

public class Ingredient {
    //ingred model, add recipe id
    private int id;
    private String name;
    private String quantity;
    private String unit;
    private String recipeid;

    public Ingredient(int id, String name, String quantity, String unit, String recipeid) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.recipeid = recipeid;
    }

    public Ingredient(String name, String quantity, String unit, String recipeid) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.recipeid = recipeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRecipeid() {
        return recipeid;
    }

    public void setRecipeid(String recipeid) {
        this.recipeid = recipeid;
    }
}
