package com.krishantha.rentcloud.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	//Don't provide authentication manager in spring boot and need to create bean for that
	//process and authenticate request
	@Bean
	protected AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}

	//these for password encodeing
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//use userdetailsservice and check the password from here
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
}
