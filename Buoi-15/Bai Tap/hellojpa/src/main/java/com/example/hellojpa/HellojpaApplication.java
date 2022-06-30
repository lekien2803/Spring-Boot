package com.example.hellojpa;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.hellojpa.model.Employer;
import com.github.javafaker.Faker;

@SpringBootApplication
public class HellojpaApplication implements CommandLineRunner {
	@Autowired private EntityManager em;
	public static void main(String[] args) {
		SpringApplication.run(HellojpaApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Faker faker = new Faker();
		for (int i = 0; i < 10; i++) {
			var employer = Employer.builder()
					.name(faker.company().name())
					.email(faker.internet().emailAddress())
					.website("https://" + faker.internet().domainName())
					.build();
			em.persist(employer);
		}
		em.flush();

	}

}
