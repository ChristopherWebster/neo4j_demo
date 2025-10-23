package com.example.demo.recipe;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class HasIngredient  {
    @RelationshipId
    String id;

    @TargetNode
    IngredientEntity ingredient;

    int quantity;
    String unit;

    public HasIngredient( IngredientEntity ingredient, int quantity, String unit) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }


    public String getId() {
        return id;
    }


    public IngredientEntity getIngredient() {
        return ingredient;
    }


    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
    @Override
    public String toString() {
        return "HasIngredient{" +
                "id='" + id + '\'' +
                ", ingredient=" + ingredient.toString() +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                '}';
    }
}
