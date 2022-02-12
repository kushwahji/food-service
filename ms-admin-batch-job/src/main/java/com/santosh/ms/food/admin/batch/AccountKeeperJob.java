package com.santosh.ms.food.admin.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.santosh.ms.food.admin.entity.Food;



@Component
public class AccountKeeperJob extends JobExecutionListenerSupport {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.file}") 
	Resource resource;
	
	@Autowired
	ItemProcessor<? super Food, ? extends Food> processor;
	
	@Autowired
	ItemWriter<? super Food> writer;
	
	@Bean(name = "productJob")
	public Job productJob() {

		Step step = stepBuilderFactory.get("step-1")
				.<Food, Food> chunk(4)
				.reader(new Reader(resource))
				.processor(processor)
				.writer(writer)
				.build();
		
		return jobBuilderFactory.get("accounting-job")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(step)
				.build();
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
