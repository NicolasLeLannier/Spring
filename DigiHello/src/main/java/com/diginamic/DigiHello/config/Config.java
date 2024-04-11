/**
 *
 */
package com.diginamic.DigiHello.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
 * @author Nicolas LE LANNIER
 */
@Configuration
public class Config {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	private String name = "Config bean";

	public Config() {
		System.out.println("Configuration constructor");
	}
	
	public String getName() {
		return name;
	}
}
