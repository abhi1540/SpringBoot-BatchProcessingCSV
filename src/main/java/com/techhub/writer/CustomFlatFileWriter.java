package com.techhub.writer;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.techhub.jobconfig.DBConfiguration;
import com.techhub.model.FinancialDataBean;

@Component
public class CustomFlatFileWriter implements ItemWriter<FinancialDataBean> {

	//https://www.petrikainulainen.net/programming/spring-framework/spring-batch-tutorial-writing-information-to-a-database-with-jdbc/
//
//	public ItemWriter<FinancialDataBean> customItemWriter(){
//		return items -> {
//			for (FinancialDataBean customer : items) {
//				System.out.println(customer.toString());
//			}
//		};
//	}
	
//	private final DBConfiguration dbconfiguration;
//	
//	public CustomFlatFileWriter(DBConfiguration dbconfiguration) {
//		this.dbconfiguration = dbconfiguration;
//	}
//	

	@Qualifier("primaryDS")
    @Autowired
    private DataSource dataSource;
	
	
	@Override
	public void write(List<? extends FinancialDataBean> items) throws Exception {
//			for (FinancialDataBean customer : items) {
//				System.out.println(customer.toString());
//			}
//		}
//		JdbcBatchItemWriter<FinancialDataBean> writer = new JdbcBatchItemWriter<FinancialDataBean>();
//		writer.setDataSource(dataSource);
//		writer.setSql("insert into financial_data values (:Series_reference,:Period,:Data_value,:Suppressed,:STATUS,:UNITS,:Magnitude,:Subject,:Group,:Series_title_1,:Series_title_2,:Series_title_3)");
//		BeanPropertyItemSqlParameterSourceProvider<FinancialDataBean> provider = new BeanPropertyItemSqlParameterSourceProvider<>();
//	    writer.setItemSqlParameterSourceProvider(provider);
//
//	    writer.afterPropertiesSet(); 
	    return;	
		
	}
	
	@Bean
    public JdbcBatchItemWriter<FinancialDataBean> customerItemWriter(){
        JdbcBatchItemWriter<FinancialDataBean> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(this.dataSource);
        writer.setSql("insert into financial_data values (:Series_reference,:Period,:Data_value,:Suppressed,:status,:units,:Magnitude,:Subject,:Group,:Series_title_1,:Series_title_2,:Series_title_3)");
		BeanPropertyItemSqlParameterSourceProvider<FinancialDataBean> provider = new BeanPropertyItemSqlParameterSourceProvider<>();
	    writer.setItemSqlParameterSourceProvider(provider);

	    writer.afterPropertiesSet(); 

        return writer;
    }


};


	

