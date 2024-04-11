/**
 *
 */
package com.diginamic.DigiHello.tools;

import org.springframework.stereotype.Component;

@Component
public class TestSingleton {
	
	private final static TestSingleton INSTANCE = new TestSingleton();
	
//	private TestSingleton() {}
	
	public static TestSingleton getInstance() {
		return INSTANCE;
	}
	
	public TestSingleton() {
		//System.out.println("TestSingleton");
	}
	
}
