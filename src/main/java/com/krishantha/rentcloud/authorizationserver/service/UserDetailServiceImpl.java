package com.krishantha.rentcloud.authorizationserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.krishantha.rentcloud.authorizationserver.model.AuthUserDetail;
import com.krishantha.rentcloud.authorizationserver.model.User;
import com.krishantha.rentcloud.authorizationserver.repository.UserDetailRepository;

//user details service interface provide by the spring security and we need to implement unimplemented method 
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Optional<User> optionalUser = userDetailRepository.findByUsername(name);
		
		//if theoptinal user not found we can throw the exception what happend here
		optionalUser.orElseThrow(()->new UsernameNotFoundException("Username or password wrong"));
		
		//map to the userdetails this also provide by spring security and this is implemented where model package AuthUserDetails
		UserDetails userDetails=new AuthUserDetail(optionalUser.get());
		
		//this for this check this user activated or nonexpired ... 
		new AccountStatusUserDetailsChecker().check(userDetails);
		return userDetails;
	}

}
