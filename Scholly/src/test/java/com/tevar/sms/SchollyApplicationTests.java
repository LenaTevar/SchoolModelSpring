package com.tevar.sms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
class SchollyApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("==== Running Test in test database, see more information in localhost:8080/h2 -  jdbc:h2:~/SchollyDB_TEST");
	}
	
	@Test
	public void main() {
		SchollyApplication.main(new String[] {});
	}

}
