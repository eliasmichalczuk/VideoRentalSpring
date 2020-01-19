package com.elias.videorental;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VideoRentalApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
class VideoRentalApplicationTests {

	@Test
	public void contextLoads() {
	    VideoRentalApplication.main(new String[] { "--spring.profiles.active=local" });
		assertThat(true).isTrue();
	}
}
