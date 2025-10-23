package com.example.demo.recipe;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Node("Step")
public class StepEntity implements Step {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;
    @Property
    private String description;
    @Property
    private int stepNumber;

    //default no arg constructor
    public StepEntity() {}

    //Constructor
    public StepEntity(String description, int stepNumber) {
        this.description = description;
        this.stepNumber = stepNumber;
    }

    @Override
    public String getId() {
        return id.toString();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getStepNumber() {
        return stepNumber;
    }
}
