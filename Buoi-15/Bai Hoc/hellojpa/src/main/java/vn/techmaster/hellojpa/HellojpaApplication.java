package vn.techmaster.hellojpa;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;

import vn.techmaster.hellojpa.model.Employer;
import vn.techmaster.hellojpa.model.Job;

@SpringBootApplication
@Transactional
public class HellojpaApplication implements ApplicationRunner {

	@Autowired private EntityManager en;
	public static void main(String[] args) {
		SpringApplication.run(HellojpaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Faker faker = new Faker();
		// for (int i = 0; i < 100; i++) {
		// 	var employer = Employer.builder()
		// 					.name(faker.name().firstName())
		// 					.email(faker.internet().emailAddress())
		// 					.website("https://" + faker.internet().domainName().toString())
		// 					.build();
		// 					en.persist(employer);
		// }
		// en.flush();
		
		for (int i = 0; i < 10; i++) {
			var job = Job.builder()
							.title(faker.name().title())
							.description(faker.pokemon().toString())
							.build();
							en.persist(job);
		}
		en.flush();
	}


}
