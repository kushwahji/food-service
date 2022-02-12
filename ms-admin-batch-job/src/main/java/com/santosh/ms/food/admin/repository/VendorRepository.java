/**
 * 
 */
package com.santosh.ms.food.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.ms.food.admin.entity.Vendor;

/**
 * @author santosh.kushwah
 *
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
