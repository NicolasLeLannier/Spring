/**
 *
 */
package com.diginamic.DigiHello.controleurs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diginamic.DigiHello.exception.FunctionalException;

/** 
 * @author Nicolas LE LANNIER
 */
@ControllerAdvice
public class ExceptionHandlerControleur {

	 @ExceptionHandler({ FunctionalException.class})
	 protected ResponseEntity<String> traiterErreurs(FunctionalException ex) {
	 return ResponseEntity.badRequest().body(ex.getMessage());
	 }
}
