package com.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {
	
	@Test
	void 더하기테스트() {
		int x = 15;
		int y = 20;
		assertEquals(x+y,35);
	}

}
