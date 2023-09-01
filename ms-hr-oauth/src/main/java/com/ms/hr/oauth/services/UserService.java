package com.ms.hr.oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ms.hr.oauth.feignclients.UserFeignClient;
import com.ms.hr.user.entities.User;


@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeingClient;
	
	public User fingByEmail(String email) {
		User user = userFeingClient.findByEmail(email).getBody();
		if (user == null) {
			logger.error("Email not found: {}", email);
			throw new IllegalArgumentException("User Not Found");
		}
		logger.info("Email found: {}", email);
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFeingClient.findByEmail(username).getBody();
		if (user == null) {
			logger.error("Email not found: {}", username);
			throw new UsernameNotFoundException("User Not Found");
		}
		logger.info("Email found: {}", username);
		return user;
	}
	
	
}
