/**
 *
 */
package com.diginamic.DigiHello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diginamic.DigiHello.config.Config;

/** 
 * @author Nicolas LE LANNIER
 */
@Service
public class UserService {

	// Pas de besoin @Autowired grâce à Spring Boot
	private Config config;
	
	public UserService(Config config) {
		this.config = config;
		System.out.println("UserService Constructor " + config.toString());
	}
	
	@Autowired
	public void setConfig(Config config) {
		this.config = config;
		System.out.println("UserService Constructor " + config.toString());
	}
}
