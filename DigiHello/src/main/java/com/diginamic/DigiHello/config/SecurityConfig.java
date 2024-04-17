/**
 *
 */
package com.diginamic.DigiHello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import com.diginamic.DigiHello.repository.UserAccountRepository;

/** 
 * @author Nicolas LE LANNIER
 */
@Configuration
public class SecurityConfig {

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//		userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build());
//		userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build());
//		return userDetailsManager;
//	}
	
	@Bean
	public UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
		return username -> userAccountRepository.findbyUsername(username).asUser();
	}
}
