package com.journal.journal_app;

import com.journal.journal_app.repository.JournalEntryRepo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class JournalAppApplication {
	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	@Autowired
	private ApplicationContext context;  // inject this

	@PostConstruct
	public void printUri() {
		System.out.println("Mongo URI = " + mongoUri);
		// Also check what Spring actually resolved:
		System.out.println("Active profiles: " +
				Arrays.toString(context.getEnvironment().getActiveProfiles()));
	}

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create(mongoUri);
	}

	public static void main(String[] args) {
		SpringApplication.run(JournalAppApplication.class, args);
	}
}
