package com.techhub.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Before Job JobId " + jobExecution.getJobInstance().getJobName());
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("After Job JobId " + jobExecution.getJobInstance().getJobName());
		
	}

}
