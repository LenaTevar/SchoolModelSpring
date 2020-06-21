package com.tevar.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchollyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchollyApplication.class, args);
		System.out.println(" ==== Running the School Manager in Spring Scholly ====");
		System.out.println(" ==== Console h2 opened in localhost:8080/h2 jdbc:h2:~/SchollyDB ====");
		System.out.println(" ==== Database for testing coverage in jdbc:h2:~/SchollyDB_TEST ====");
	}

}
