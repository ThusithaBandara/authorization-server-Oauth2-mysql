package com.krishantha.rentcloud.authorizationserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krishantha.rentcloud.authorizationserver.model.User;

public interface UserDetailRepository extends JpaRepository<User, Integer> {
	//we use optional and we dont need to handle null
	Optional< User> findByUsername(String name);

}
