/**
 *
 */
package com.diginamic.DigiHello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.diginamic.DigiHello.repository.UserAccountRepository;

/**
 * @author Nicolas LE LANNIER
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//		userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build());
//		userDetailsManager.createUser(User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build());
//		return userDetailsManager;
//	}

	@Bean
	UserDetailsService userDetailsService(UserAccountRepository userAccountRepository) {
		return username -> userAccountRepository.findByUsername(username).asUser();
	}

	@Bean
	protected SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request -> request.requestMatchers("/login", "/login/oauth2/code/google").permitAll()
				.requestMatchers("/", "/hello").authenticated().requestMatchers("/villes/deleteTown/")
				.hasRole("ADMIN").requestMatchers(HttpMethod.GET, "/towns/").authenticated()
				.requestMatchers(HttpMethod.POST, "/towns/**").hasRole("ADMIN").anyRequest().denyAll())
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults())
				.oauth2Login(Customizer.withDefaults());
		return http.build();
	}

}
