package com.example.demo.repository;

import com.example.demo.recipe.RecipeEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends Neo4jRepository<RecipeEntity, String> {
    void deleteByRecipeId(String recipeId);

    @Override
    void delete(RecipeEntity entity);
}
