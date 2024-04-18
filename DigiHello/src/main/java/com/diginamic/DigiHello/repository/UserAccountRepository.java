/**
 *
 */
package com.diginamic.DigiHello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.diginamic.DigiHello.model.UserAccount;

/** 
 * @author Nicolas LE LANNIER
 */
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
	UserAccount findByUsername(String username);
}
