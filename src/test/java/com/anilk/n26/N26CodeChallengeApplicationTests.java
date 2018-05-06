package com.anilk.n26;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(value = { "classpath:application-test.properties" })
public class N26CodeChallengeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
