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

        //fail("Not yet implemented");
        StepEntity step1 = new StepEntity("Chop the onions", 1);
        StepEntity step2 = new StepEntity("Fry the onions", 2);
        ArrayList<StepEntity> steps = new ArrayList<StepEntity>();
        steps.add(step1);
        steps.add(step2);

        IngredientEntity onions = new IngredientEntity("Onion");
        IngredientEntity oil = new IngredientEntity("Oil");
        ArrayList<HasIngredient> ingredients = new ArrayList<HasIngredient>();
        ingredients.add(new HasIngredient(onions, 2, "pieces"));
        ingredients.add(new HasIngredient(oil, 30, "ml"));

        RecipeEntity recipe = new RecipeEntity("Fried Onions", "A simple recipe for fried onions",ingredients,steps);
        RecipeEntity result = recipeService.save(recipe);
        assert(recipeService.recipeCount() == 1);
        recipeId = result.getRecipeId();
        assert(result.getRecipeId() != null);
        assert(result.getIngredients().iterator().next().getIngredient().getName().equals(onions.getName()));
        assert(result.getIngredients().iterator().next().getQuantity() == 2);
        System.out.println(result.getIngredients().iterator().next().getQuantity());
        System.out.println("Recipe ID: " + result.getRecipeId()+ " UUID: " + result.getRecipeId());
        System.out.println("Ingredients: " + result.getIngredients().iterator().next().toString());

        System.out.println("Now Delete Recipe ID: " + recipeId);
        //recipeService.deleteById(recipeId);
        //assert(recipeService.recipeCount() == 0);
    }
}