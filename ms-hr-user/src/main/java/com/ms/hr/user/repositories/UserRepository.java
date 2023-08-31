package com.ms.hr.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.hr.user.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail (String email);

}
