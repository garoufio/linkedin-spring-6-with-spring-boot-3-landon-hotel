package com.linkedin.landon_hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class LandonHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(LandonHotelApplication.class, args);
	}

}
