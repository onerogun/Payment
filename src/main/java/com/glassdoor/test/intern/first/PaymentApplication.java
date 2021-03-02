package com.glassdoor.test.intern.first;

import com.glassdoor.test.intern.first.entity.User;
import com.glassdoor.test.intern.first.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
@SpringBootApplication
public class PaymentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	/**
	 *Save users to H2 in-memory database after application starts
	 *
	 */
	@Override
	public void run(String... args) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				PaymentApplication.class.getClassLoader().getResourceAsStream("user_database.txt")))) {

			String line;
			while ((line = br.readLine()) != null) {
				log.info("adding user to database: " + line);
				String splits[] = line.split("\t");

				User user = new User();
				user.setUserId(Long.parseLong(splits[0]));
				user.setUserName(splits[1]);
				user.setAddress(splits[2]);
				userRepository.save(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
