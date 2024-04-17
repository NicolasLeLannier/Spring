/**
 *
 */
package com.diginamic.DigiHello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diginamic.DigiHello.model.UserAccount;

/** 
 * @author Nicolas LE LANNIER
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	public UserAccount findbyUsername(String username);
}
