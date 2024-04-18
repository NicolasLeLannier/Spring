/**
 *
 */
package com.diginamic.DigiHello.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** 
 * @author Nicolas LE LANNIER
 */
@Entity
public class UserAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	/** Constructeur
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public UserAccount(String username, String password, UserRole role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/** Constructeur
	 * 
	 */
	public UserAccount() {
		super();
	}

	/** Getter
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/** Getter
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/** Setter
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** Getter
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/** Setter
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public UserDetails asUser() {
		return User.withDefaultPasswordEncoder()
			.username(getUsername())
			.password(getPassword())
			.authorities(getRole().name())
			.build();
	}

	/** Getter
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}

	/** Setter
	 * @param role the role to set
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}
}
