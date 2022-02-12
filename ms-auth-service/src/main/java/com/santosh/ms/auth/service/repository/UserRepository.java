/**
 * 
 */
package com.santosh.ms.auth.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.santosh.ms.auth.service.entity.User;

/**
 * @author santosh.kushwah
 * @since 11-02-2022
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);	
}
