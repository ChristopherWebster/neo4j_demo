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

        //Now get the recipe Id from the result object, which is the saved recipe id
        recipeId = result.getRecipeId();

        // Read back the recipe from the database and validate the contents
        RecipeEntity read_recipe = recipeService.findById(recipeId);
        assert(read_recipe.getRecipeId() != null);
        assert(read_recipe.getIngredients().iterator().next().getIngredient().getName().equals(oil.getName()));
        assert(read_recipe.getIngredients().iterator().next().getQuantity() == 30);

        // Let's see some output
        System.out.println("Read Recipe has ingredients["+read_recipe.getIngredients().size()+"] ");
        System.out.println("Recipe ID: " + read_recipe.getRecipeId()+ " Name: " + read_recipe.getName());
        System.out.println("Ingredients: " + read_recipe.getIngredients().iterator().next().toString());
        System.out.println("Now Delete Recipe ID: " + read_recipe.getRecipeId());

        //finally delete the recipe
        //recipeService.deleteById(read_recipe.getRecipeId());
        //recipeService.delete(read_recipe);
        //assert(recipeService.recipeCount() == 0);
    }
}
