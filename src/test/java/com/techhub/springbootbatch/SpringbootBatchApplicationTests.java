package com.techhub.springbootbatch;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootTest
class SpringbootBatchApplicationTests {
	
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	void contextLoads() throws Exception {
		
		JobParameters jobParameter = new JobParametersBuilder()
				.addParameter("outputText", new JobParameter("HelloWorld"))
				.toJobParameters();
		
		jobLauncherTestUtils.launchJob(jobParameter);
	}
	
	//https://howtodoinjava.com/spring-core/spring-configuration-annotation/
	@Configuration
	@EnableBatchProcessing
	static class TestConfig {
		
		@Autowired
		private JobBuilderFactory jobBuilderFactory;
		
		@Autowired
		private StepBuilderFactory stepBuilderFactory;
		
		
		@Bean
		public Job myFirstjob() {
			
			TaskletStep step = stepBuilderFactory.get("step")
					.tasklet(new Tasklet() {
						
						@Override
						public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
							Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
							Object outputTecxt = jobParameters.get("outputText");
							System.out.println(outputTecxt);
							return RepeatStatus.FINISHED;
						}
					}).build();
			
			return jobBuilderFactory.get("hellowworldjob")
					.start(step)
					.build();
		}
		
		
		
		@Bean
		public JobLauncherTestUtils utils() {
			
			return new JobLauncherTestUtils();
		}
		
	}

}
