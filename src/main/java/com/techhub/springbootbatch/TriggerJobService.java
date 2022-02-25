package com.techhub.springbootbatch;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import com.techhub.jobconfig.BatchJob;



@Component
public class TriggerJobService {
	
	private final JobLauncher jobLauncher;
	private final Job job;
	
	public TriggerJobService(JobLauncher jobLauncher, @BatchJob Job job) {
		super();
		this.jobLauncher = jobLauncher;
		this.job = job;
	}


	public void runJob() throws JobExecutionAlreadyRunningException,
	JobRestartException,
	JobInstanceAlreadyCompleteException, 
	JobParametersInvalidException {

		
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());
				
		jobLauncher.run(job, builder.toJobParameters());
	}



}
