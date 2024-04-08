/**
 *
 */
package com.diginamic.DigiHello.service;

import org.springframework.stereotype.Service;

/** 
 * @author Nicolas LE LANNIER
 */
@Service
public class HelloService {

	public String salutations() {
		return "Je suis la classe de service et je vous dis Bonjour";
	}
}
