package com.example.demo;

import com.example.demo.recipe.*;

import com.example.demo.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.UUID;


@SpringBootTest
@ActiveProfiles("test")
class PersistenceTest {

    @Autowired
    RecipeService recipeService;
    String recipeId;

    @Test
    public void TestWrite()
    {

        /* A quick test suite to test the persistence process */
        ChristopherWebster

        /* Build a collection of Recipe Steps called 'steps'  */
        StepEntity step1 = new StepEntity("Chop the onions", 1);
        StepEntity step2 = new StepEntity("Fry the onions", 2);
        ArrayList<StepEntity> steps = new ArrayList<StepEntity>();
        steps.add(step1);
        steps.add(step2);

        /* Build a collection of HasIngredients called 'ingredients'  */
        /* where each HasIngredient contains an IngredientEntity and  */
        /* 2 parameters, quantity and unit                            */
        IngredientEntity onions = new IngredientEntity("Onion");
        IngredientEntity oil = new IngredientEntity("Oil");
        ArrayList<HasIngredient> ingredients = new ArrayList<HasIngredient>();
        ingredients.add(new HasIngredient(onions, 2, "pieces"));
        ingredients.add(new HasIngredient(oil, 30, "ml"));

        /* Now create a recipe of type RecipeEntity containing a name, description, ingredients and steps         */
        RecipeEntity recipe = new RecipeEntity("Fried Onions", "A simple recipe for fried onions",ingredients,steps);

        /* Finally we save the recipe to the neo4j database */
        RecipeEntity result = recipeService.save(recipe);
        
        //Check the recipe count - assuming we're writing to an empty Neo4j instance 
        assert(recipeService.recipeCount() == 1);

        //Now get the recipe Id from teh result object
        recipeId = result.getRecipeId();
    
        assert(result.getRecipeId() != null);
        assert(result.getIngredients().iterator().next().getIngredient().getName().equals(onions.getName()));
        assert(result.getIngredients().iterator().next().getQuantity() == 2);
        /* Lets check some output*/
        System.out.println(result.getIngredients().iterator().next().getQuantity());
        System.out.println("Recipe ID: " + result.getRecipeId()+ " UUID: " + result.getRecipeId());
        System.out.println("Ingredients: " + result.getIngredients().iterator().next().toString());
        System.out.println("Now Delete Recipe ID: " + recipeId);
        
        recipeService.deleteById(recipeId);
        assert(recipeService.recipeCount() == 0);
    }
}
