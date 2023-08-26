package com.ms.hrworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsHrWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHrWorkerApplication.class, args);
	}

}
