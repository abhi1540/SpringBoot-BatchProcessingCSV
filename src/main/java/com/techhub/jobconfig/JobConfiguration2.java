package com.techhub.jobconfig;

import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class JobConfiguration2 {
	
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	
	public JobConfiguration2(JobBuilderFactory jobBuilderFactory, 
			StepBuilderFactory stepBuilderFactory) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
	}


	@Bean 
	public Job mySecondjob() {
		
		TaskletStep step = stepBuilderFactory.get("step")
				.tasklet(new Tasklet() {
					
					@Override
					public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
						Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
						Object outputTecxt = jobParameters.get("outputText");
						System.out.println("my second job" + outputTecxt);
						return RepeatStatus.FINISHED;
					}
				}).build();
		
		return jobBuilderFactory.get("mySecondjob")
				.start(step)
				.build();
	}
	

}
