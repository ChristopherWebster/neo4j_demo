package com.example.demo.recipe;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Node("Recipe")
public class RecipeEntity {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String recipeId;
    private String name;
    private String description;


    @Relationship(type = "HasIngredient",  direction = Relationship.Direction.OUTGOING)
    private List<HasIngredient> ingredients = new ArrayList<HasIngredient>();
    @Relationship(type = "HAS_STEP", direction = Relationship.Direction.OUTGOING)
    private ArrayList<StepEntity> steps;

    //default no arg constructor
    public RecipeEntity() {}

    //Constructor

    public RecipeEntity( String name, String description, ArrayList<HasIngredient> ingredients, ArrayList<StepEntity> steps) {
        this.recipeId = name;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getRecipeId() {
        return recipeId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<HasIngredient> getIngredients() {
        return ingredients;
    }
    public List<StepEntity> getSteps() {
        return steps;
    }

    public void setIngredients(List<HasIngredient> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }

    public void setSteps(ArrayList<StepEntity> steps) {
        this.steps = new ArrayList<>(steps);
    }
}
