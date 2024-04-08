/**
 *
 */
package com.diginamic.DigiHello.config;

import org.springframework.context.annotation.Configuration;

/** 
 * @author Nicolas LE LANNIER
 */
@Configuration
public class Config {
	
	private String name = "Config bean";

	public Config() {
		System.out.println("Configuration constructor");
	}
	
	public String getName() {
		return name;
	}
}
