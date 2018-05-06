package com.anilk.n26;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class N26CodeChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(N26CodeChallengeApplication.class, args);
	}
}
