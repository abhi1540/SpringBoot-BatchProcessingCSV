package com.techhub.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.techhub.model.FinancialDataBean;

@Component
public  class CustomFlatFileItemReader extends MultiResourceItemReader<FinancialDataBean>{
	
//	@Value("classpath*:/static/*.csv")
//	private Resource[] inputFiles;
	
	
	public  FlatFileItemReader<FinancialDataBean> flatFileItemReader() {
		FlatFileItemReader<FinancialDataBean> flatFileItemReader =
				new FlatFileItemReader<FinancialDataBean>();
		
		flatFileItemReader.setLineMapper(new DefaultLineMapper<FinancialDataBean>() {
			
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames("Series_reference","Period","Data_value","Suppressed","status","units","Magnitude","Subject","Group","Series_title_1","Series_title_2","Series_title_3");							
					}
				});
				
				setFieldSetMapper(new BeanWrapperFieldSetMapper<FinancialDataBean>() {
					{
					setTargetType(FinancialDataBean.class);
					}
				});
			}
		});
		
		flatFileItemReader.setLinesToSkip(1);
		
		return flatFileItemReader;
		

}
	

	public MultiResourceItemReader<FinancialDataBean> multiResourceItemreader(Resource[] inputFiles) {
		MultiResourceItemReader<FinancialDataBean> reader = new MultiResourceItemReader<FinancialDataBean>();
		System.out.println(inputFiles);
		reader.setDelegate(flatFileItemReader());
		reader.setResources(inputFiles);
		return reader;
	}





	
	

}
