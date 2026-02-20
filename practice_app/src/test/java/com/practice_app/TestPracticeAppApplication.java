package com.practice_app;

import org.springframework.boot.SpringApplication;

public class TestPracticeAppApplication {

	public static void main(String[] args) {
		SpringApplication.from(PracticeAppApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
