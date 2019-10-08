package com.krishantha.rentcloud.authorizationserver.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


@Configuration
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	DataSource dataSource;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Bean
	TokenStore JdbcTokenStore() {
		return new JdbcTokenStore(dataSource);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
	}

	//get clients details from database
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		//come details also store in database 
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		//The AuthenticationManager for the password grant.
		endpoints.tokenStore(JdbcTokenStore());
		endpoints.authenticationManager(authenticationManager);
	}

}
