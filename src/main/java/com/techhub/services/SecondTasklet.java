package com.techhub.services;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service
public class SecondTasklet implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
		Object outputTecxt = jobParameters.get("outputText");
		System.out.println("my Second job" + outputTecxt);
		return RepeatStatus.FINISHED;
	}

}
