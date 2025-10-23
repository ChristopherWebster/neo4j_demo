package com.example.demo;

import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;

@SpringBootApplication
public class Neo4j_Demo implements CommandLineRunner {

	@Autowired
	Driver driver;

	public static void main(String[] args) {
		SpringApplication.run(Neo4j_Demo.class, args);
	}
	@Bean
	public Configuration configuration() {
		return Configuration.newConfig().withDialect(Dialect.NEO4J_5).build();
	}
	@Override
	public void run(String... args) throws Exception {
		Initialize.INSTANCE.InitializeDatabase(driver);
	}
}