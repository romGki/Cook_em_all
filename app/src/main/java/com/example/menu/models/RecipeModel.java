package com.example.menu.models;

public class RecipeModel
{
    private int id;
    private String name;
    private String intro;
    private String description;
    private String ingredients;
    private String instructions;

    private String time;
    private String difficulty;
    private String servings;
    private String type;
    private String mainIngredient;
    private int image;

    public RecipeModel(int id, String name, String intro, String ingredients, String instructions, String time, int image, String difficulty, String servings, String type, String mainIngredient) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.image = image;
        this.time = time;
        this.difficulty = difficulty;
        this.servings = servings;
        this.type=type;
        this.mainIngredient=mainIngredient;
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

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type=type;
    }

    public String getMainIngredient()
    {
        return mainIngredient;
    }

    public void setMainIngredient(String mainIngredient)
    {
        this.mainIngredient=mainIngredient;
    }


}
