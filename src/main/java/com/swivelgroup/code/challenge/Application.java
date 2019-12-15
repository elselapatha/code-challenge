package com.swivelgroup.code.challenge;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.impl.CommandLineServiceImpl;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Value("${job.autorun.enabled}")
	private boolean autorun;

	private  CommandLineServiceImpl testService;

	public Application() {
		testService = new CommandLineServiceImpl();
	}

	public static void main(String[] args) {SpringApplication.run(Application.class, args);}

	@Override
	public void run(String... args) throws Exception {
		if(autorun) {
			System.out.println("---------- Welcome To Code Challenge ----------\n");
			testService.start();
			System.out.println("\n---------- Good Bye ----------");
		}
	}
}
