package com.example.masakapah.Model;

import java.io.Serializable;

public class ModelDetail implements Serializable {
    private String Ingredient;
    private String Step;


    public String getIngredient() {
        return Ingredient;
    }

    public String getStep() {
        return Step;
    }

    public void setIngredient(String ingredient) {
        Ingredient = ingredient;
    }

    public void setStep(String step) {
        Step = step;
    }
}
