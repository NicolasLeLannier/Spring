/**
 *
 */
package com.diginamic.DigiHello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diginamic.DigiHello.service.VilleService;

/** 
 * @author Nicolas LE LANNIER
 */
@SpringBootApplication
public class MyCommandLineRunner implements CommandLineRunner {

	@Autowired
	VilleService villeService;
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(DigiHelloApplication.class);
		 application.setWebApplicationType(WebApplicationType.NONE);
		 application.run(args);
	}

	 @Override
	 public void run(String... args) throws Exception {
		 System.out.println("Inside Run");
//		 villeService.exportToCsvOnDisk();
	 }
}
