package com.ms.hr.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsHrUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHrUserApplication.class, args);
	}

}
