package com.tequip.superchef;

// class to get each value for a specific recipe
public class recipe_model_class {
    public String id, recipeName, recipeImage, usedIngredients, unusedIngredients, missingIngredients;

    public recipe_model_class(String id, String recipeName, String recipeImage, String usedIngredients, String unusedIngredients, String missingIngredients) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.usedIngredients = usedIngredients;
        this.unusedIngredients = unusedIngredients;
        this.missingIngredients = missingIngredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getUsedIngredients() {
        return usedIngredients;
    }

    public void setUsedIngredients(String usedIngredients) {
        this.usedIngredients = usedIngredients;
    }

    public String getUnusedIngredients() {
        return unusedIngredients;
    }

    public void setUnusedIngredients(String unusedIngredients) {
        this.unusedIngredients = unusedIngredients;
    }

    public String getMissingIngredients() {
        return missingIngredients;
    }

    public void setMissingIngredients(String missingIngredients) {
        this.missingIngredients = missingIngredients;
    }


}
