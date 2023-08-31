package com.ms.hr.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsHrOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHrOauthApplication.class, args);
	}

}
