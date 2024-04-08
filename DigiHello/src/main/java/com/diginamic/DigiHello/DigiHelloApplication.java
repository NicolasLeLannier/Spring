package com.diginamic.DigiHello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diginamic.DigiHello.config.Config;
import com.diginamic.DigiHello.tools.TestSingleton;

@SpringBootApplication
public class DigiHelloApplication {
	
	@Autowired
	Config config;

	public static void main(String[] args) {
		SpringApplication.run(DigiHelloApplication.class, args);
		
//		TestSingleton ts = TestSingleton.getInstance();
//		TestSingleton ts2 = TestSingleton.getInstance();
//		
//		if(ts == ts2) {
//			System.out.println("C'est la même instance");
//		}
	}

}
 