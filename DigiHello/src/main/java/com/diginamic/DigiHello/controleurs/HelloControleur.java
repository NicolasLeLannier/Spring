/**
 *
 */
package com.diginamic.DigiHello.controleurs;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

/** 
 * @author Nicolas LE LANNIER
 */
@RestController
@RequestMapping("/hello")
public class HelloControleur {
	
	@GetMapping
	public String direHello() {
		return "Hello";
	}
	
}
