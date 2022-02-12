/**
 * 
 */
package com.santosh.ms.food.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santosh.ms.food.admin.controller.VendorService;
import com.santosh.ms.food.admin.dto.VendorDto;
import com.santosh.ms.food.admin.entity.Vendor;
import com.santosh.ms.food.admin.repository.VendorRepository;

/**
 * @author santosh.kushwah
 *
 */
@Service
public class VendorServiceImpl implements VendorService{

	@Autowired
	VendorRepository repo;
	
	@Override
	public VendorDto save(VendorDto request) {
		return new VendorDto(repo.save(new Vendor(request)));
	}

}
