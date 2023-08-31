package com.ms.config.server;

//import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MsHrConfigServerApplication implements CommandLineRunner {
	
//	@Value("${spring.cloud.config.server.git.username}")
//	String user;
//	@Value("${spring.cloud.config.server.git.password}")
//	String pass;
	

	public static void main(String[] args) {
		SpringApplication.run(MsHrConfigServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println("GIT USER: " + user + " GIT PASS: " + pass);		
	}

}
