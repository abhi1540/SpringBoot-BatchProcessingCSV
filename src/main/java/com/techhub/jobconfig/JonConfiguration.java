package com.techhub.jobconfig;

import java.io.File;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.techhub.listener.FirstJobListener;
import com.techhub.listener.FirstStepListener;
import com.techhub.model.FinancialDataBean;
import com.techhub.reader.CustomFlatFileItemReader;
import com.techhub.services.FirstChunkTasklet;
import com.techhub.services.FirstTasklet;
import com.techhub.services.SecondTasklet;
import com.techhub.writer.CustomFlatFileWriter;

@PropertySource("classpath:application.properties")
@Configuration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class JonConfiguration {
	
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final FirstTasklet firstTasklet;
	private final SecondTasklet secondtasklet; 
	private final FirstJobListener firstJobListener;
	private final FirstStepListener firstStepListener;
	private final FirstChunkTasklet firstChunkTasklet;
	private final CustomFlatFileItemReader customFlatFileItemReader;
	private final CustomFlatFileWriter customFlatFileWriter;

	
	
	public JonConfiguration(JobBuilderFactory jobBuilderFactory, 
			StepBuilderFactory stepBuilderFactory,  
			FirstTasklet firstTasklet,
			SecondTasklet secondtasklet, FirstJobListener firstJobListener,
			FirstStepListener firstStepListener, FirstChunkTasklet firstChunkTasklet, CustomFlatFileItemReader customFlatFileItemReader, CustomFlatFileWriter customFlatFileWriter) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.firstTasklet = firstTasklet;
		this.secondtasklet= secondtasklet;
		this.firstJobListener = firstJobListener;
		this.firstStepListener = firstStepListener;
		this.firstChunkTasklet = firstChunkTasklet;
		this.customFlatFileItemReader = customFlatFileItemReader;
		this.customFlatFileWriter = customFlatFileWriter;


	}
	
	@Value("F:\\Eclipse_Workspace\\ZipFilesBatchProcessing\\*\\*.csv")
//    private String fileSystemResource;
//	@Value("classpath*:/static/*.csv")
	private Resource[] inputFiles;

//	@Bean 
//	@BatchJob
//	public Job myFirstjob() {
//		
//		return jobBuilderFactory.get("myFirstjob")
//				.incrementer(new RunIdIncrementer())
//				.start(firstStep())
//				.next(secondStep())
//				.listener(firstJobListener)
//				.build();
//	}
//	
	
//	private Step firstStep() {
//		return stepBuilderFactory.get("firstStep")
//				.tasklet(firstTasklet)
//				.listener(firstStepListener)
//				.build();
//	
//	}
//	
//	private Step secondStep() {
//		return stepBuilderFactory.get("secondStep")
//				.tasklet(secondtasklet)
//				.build();
//	
//	}
	
	@Bean
	@BatchJob
	public Job firstChunkJob() {
		return jobBuilderFactory.get("firstChunkJob")
				.incrementer(new RunIdIncrementer())
				.start(csvUnzippingStep())
				.next(firstChunkStep())
				.build();
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	private Step firstChunkStep() {
		
		return stepBuilderFactory.get("firstChunkStep")
				.<FinancialDataBean, FinancialDataBean>chunk(100)
				.reader(customFlatFileItemReader.multiResourceItemreader(inputFiles))
				//.processor()
				.writer(customFlatFileWriter.customerItemWriter())
				.build();
	}
	


	
	private Step csvUnzippingStep() {
		System.out.println("I amgetting executed");
		return stepBuilderFactory.get("csvReaderStep")
				.tasklet(firstChunkTasklet)
				.build();
		
	}
	
//	@Bean
//	public MultiResourceItemReader<FinancialDataBean> multiResourceItemReader() {
//		return new CustomFlatFileItemReader().multiResourceItemreader();
//	}
//
//
//
//	@Bean
//	public ItemWriter<FinancialDataBean> customFlatFileWriter(){
//		return CustomFlatFileWriter.customItemWriter();
//	}
	
//	private Tasklet firstTask() {
//		return new Tasklet() {
//			
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//				// TODO Auto-generated method stub
//				
//			}
//		};
//		
//	}
//	@Bean
//	@StepScope
//	public FlatFileItemReader<FinancialDataBean> flatFileItemReader() {
//		FlatFileItemReader<FinancialDataBean> flatFileItemReader =
//				new FlatFileItemReader<FinancialDataBean>();
//		
//		//flatFileItemReader.setResource(new FileSystemResource(new File("F:\\Eclipse_Workspace\\springboot-batch\\springboot-batch\\src\\main\\resources\\static\\400to600.csv")));
//		
//
//		//flatFileItemReader.setResource(resourceLoader.getResource("classpath:400to600.csv"));
//
//		
//		flatFileItemReader.setLineMapper(new DefaultLineMapper<FinancialDataBean>() {
//			
//			{
//				setLineTokenizer(new DelimitedLineTokenizer() {
//					{
//						setNames("Series_reference","Period","Data_value","Suppressed","STATUS","UNITS","Magnitude","Subject","Group","Series_title_1","Series_title_2","Series_title_3");							
//					}
//				});
//				
//				setFieldSetMapper(new BeanWrapperFieldSetMapper<FinancialDataBean>() {
//					{
//					setTargetType(FinancialDataBean.class);
//					}
//				});
//			}
//		});
//		
//		flatFileItemReader.setLinesToSkip(1);
//		
//		return flatFileItemReader;
		
//
//}
//	
//	public FlatFileItemWriter<FinancialDataBean> flatFileItemWriter(){
//		FlatFileItemWriter<FinancialDataBean> flatFileItemWriter =new FlatFileItemWriter<FinancialDataBean>();
//		return flatFileItemWriter;
//		
//	}
	
	
//	
//	@Bean
//	public MultiResourceItemReader<FinancialDataBean> multiResourceItemreader() {
//		MultiResourceItemReader<FinancialDataBean> reader = new MultiResourceItemReader<>();
//		reader.setDelegate(flatFileItemReader());
//		reader.setResources(inputFiles);
//		return reader;
//	}


//	@Bean
//	public FlatFileItemReader<Customer> customerItemReader() {
//		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//		tokenizer.setNames(new String[] { "id", "firstName", "lastName", "birthdate" });
//
//		DefaultLineMapper<Customer> customerLineMapper = new DefaultLineMapper<>();
//		customerLineMapper.setLineTokenizer(tokenizer);
//		customerLineMapper.setFieldSetMapper(new CustomerFieldSetMapper());
//		customerLineMapper.afterPropertiesSet();
//
//		FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
//		reader.setLineMapper(customerLineMapper);
//		return reader;
//	}


//	@Bean
//	public ItemWriter<FinancialDataBean> customerItemWriter(){
//		return items -> {
//			for (FinancialDataBean customer : items) {
//				System.out.println(customer.toString());
//			}
//		};
//	}
//	
	
	
	
	
}
