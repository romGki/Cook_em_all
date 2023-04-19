package com.example.menu.models;

public class RecipeModel
{
    private int id;
    private String name;
    private String description;
    private String ingredients;
    private String instructions;
    private String image;

    public RecipeModel(int id, String name, String description, String ingredients, String instructions, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
