/**
 *
 */
package com.diginamic.DigiHello.controleurs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diginamic.DigiHello.service.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

/** 
 * @author Nicolas LE LANNIER
 */
@RestController
@RequestMapping("/hello")
public class HelloControleur {
	
	 @Autowired
	 private HelloService helloService;
	
	public HelloControleur() {
		System.out.println("Construction de HelloController");
	}
	
	@GetMapping
	public String direHello() {
		return helloService.salutations();
	}
	
	@GetMapping("/truc")
	public String sayHelloTruc() {
		return "Hello truc !";
	}
}
