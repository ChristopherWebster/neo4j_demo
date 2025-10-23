package com.example.demo.recipe;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Node("Ingredient")
public class IngredientEntity implements Ingredient{

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private String name;

    //default no arg constructor
    public IngredientEntity() {}

    //Constructor
    public IngredientEntity(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    public String toString() {
        return "Ingredient{id='" + id + "', name='" + name + "'}";
    }
}
