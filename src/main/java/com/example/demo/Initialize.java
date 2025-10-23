package com.example.demo;

import org.neo4j.driver.Driver;

public enum Initialize {

    INSTANCE;

    private boolean initialized = false;
    public void InitializeDatabase(Driver driver) {
        System.out.println("Initializing database...");
        try (var session = driver.session()) {
            {
                String createDataQuery = """
                      CREATE (TheMatrix:Movie {title:'The Matrix', released:1999, tagline:'Welcome to the Real World'})
                      CREATE (Keanu:Person {name:'Keanu Reeves', born:1964})
                      CREATE (Carrie:Person {name:'Carrie-Anne Moss', born:1967})
                      CREATE (Laurence:Person {name:'Laurence Fishburne', born:1961})
                      CREATE (Hugo:Person {name:'Hugo Weaving', born:1960})
                      CREATE (LillyW:Person {name:'Lilly Wachowski', born:1967})
                      CREATE (LanaW:Person {name:'Lana Wachowski', born:1965})
                      CREATE (JoelS:Person {name:'Joel Silver', born:1952})
                      CREATE
                      (Keanu)-[:ACTED_IN {roles:['Neo']}]->(TheMatrix),
                      (Carrie)-[:ACTED_IN {roles:['Trinity']}]->(TheMatrix),
                      (Laurence)-[:ACTED_IN {roles:['Morpheus']}]->(TheMatrix),
                      (Hugo)-[:ACTED_IN {roles:['Agent Smith']}]->(TheMatrix),
                      (LillyW)-[:DIRECTED]->(TheMatrix),
                      (LanaW)-[:DIRECTED]->(TheMatrix),
                      (JoelS)-[:PRODUCED]->(TheMatrix)
    
                      CREATE (Emil:Person {name:"Emil Eifrem", born:1978})
                      CREATE (Emil)-[:ACTED_IN {roles:["Emil"]}]->(TheMatrix)
                        """;
                //var result = session.run(createDataQuery);
                var result = session.run("MATCH (n) RETURN count(n) AS nodeCount");
                var record = result.single();
                System.out.println("Number of nodes in the database: " + record.get("nodeCount").asInt());
            }
        }
    }
}
