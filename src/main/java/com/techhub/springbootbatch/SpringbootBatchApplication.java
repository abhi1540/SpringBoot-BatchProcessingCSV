package com.techhub.springbootbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.techhub.jobConfig", "com.techhub.springbootbatch","com.techhub.services", "com.techhub.listener","com.techhub.reader", "com.techhub.writer"})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
@EnableBatchProcessing
public class SpringbootBatchApplication {


	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		ConfigurableApplicationContext appContext = 
		SpringApplication.run(SpringbootBatchApplication.class, args);
		
		TriggerJobService triggerJobService = appContext.getBean(TriggerJobService.class);
		//Job configJobtype = appContext.getBean("myFirstjob", Job.class);
		
		triggerJobService.runJob();
		
		
	}

}
