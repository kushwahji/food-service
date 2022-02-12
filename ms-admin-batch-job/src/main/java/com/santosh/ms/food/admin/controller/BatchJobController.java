package com.santosh.ms.food.admin.controller;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.santosh.ms.food.admin.dto.VendorDto;

@RestController
public class BatchJobController {

	@Autowired
	VendorService vendorService;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("productJob")
	Job accountKeeperJob;

	@GetMapping("/run-batch-job")
	public String handle() throws Exception {

		JobParametersBuilder jobParameters = new JobParametersBuilder().addDate("date", new Date());
		jobLauncher.run(accountKeeperJob, jobParameters.toJobParameters());
		return "Batch job has been invoked";
	}

	@PostMapping("/vendor")
	public VendorDto addVendor(@RequestBody VendorDto request) {
		return vendorService.save(request);
	}
}