package com.example.demo.service;

import com.example.demo.recipe.RecipeEntity;
import com.example.demo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeEntity save(RecipeEntity recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteById(String recipeId) {
        recipeRepository.deleteByRecipeId(recipeId);
    }

    public long recipeCount() {
        return recipeRepository.count();
    }
}
