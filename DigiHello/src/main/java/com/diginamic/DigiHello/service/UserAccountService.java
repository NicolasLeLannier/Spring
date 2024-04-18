/**
 *
 */
package com.diginamic.DigiHello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diginamic.DigiHello.model.UserAccount;
import com.diginamic.DigiHello.model.UserRole;
import com.diginamic.DigiHello.repository.UserAccountRepository;

import jakarta.annotation.PostConstruct;

/**
 * @author Nicolas LE LANNIER
 */
@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository userAccountRepository;

	@PostConstruct
	public void init() {
//		UserRole ur = null;
//		userAccountRepository.save(new UserAccount("user", "user", ur.ROLE_USER));
//		userAccountRepository.save(new UserAccount("admin", "admin", ur.ROLE_ADMIN));
	}
}
