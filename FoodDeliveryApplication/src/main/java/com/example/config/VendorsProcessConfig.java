package com.example.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.entity.Items;
import com.example.processor.ValidationItemProcessor;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class VendorsProcessConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Value("classPath:/input/FoodDeliverySystem.csv")
	private Resource inputResource;

	@Autowired
	DataSource dataSource;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	private Job readCSVJob;

	//@Scheduled(cron = "0 */1 * * * ?")
	public void performReadCSVFileJob() throws Exception {
		System.out.println("Starting the job: readCSVFileJob");
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(readCSVJob, params);
	}

	@Bean(name = "readCSVJob")	
	public Job readCSVFileJob() {
		return jobBuilderFactory.get("readCSVFileJob").incrementer(new RunIdIncrementer()).start(readCSVFileStep()).build();
	}

	@Bean
	public Step readCSVFileStep() {
		return stepBuilderFactory.get("readCSVFileStep").<Items, Items>chunk(5).reader(readCSVFileReader()).processor(readCSVFileProcessor())
				.writer(jpaItemsFromCSVFileWriter()).build();
	}

	@Bean
	public ItemProcessor<Items, Items> readCSVFileProcessor() {
		return new ValidationItemProcessor();
	}

	@Bean
	public FlatFileItemReader<Items> readCSVFileReader() {
		FlatFileItemReader<Items> itemReader = new FlatFileItemReader<>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(inputResource);
		
		return itemReader;
	}

	@Bean
	public LineMapper<Items> lineMapper() {
		DefaultLineMapper<Items> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("ItemName","ItemDesc","Price","vid");
		lineTokenizer.setIncludedFields(0,1,2,3);
		BeanWrapperFieldSetMapper<Items> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Items.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	@Bean
	public JpaItemWriter<Items> jpaItemsFromCSVFileWriter() {
		JpaItemWriter<Items> jpaEmpWriter = new JpaItemWriter<>();
		jpaEmpWriter.setEntityManagerFactory(entityManagerFactory);
		return jpaEmpWriter;
	}

	/*
	 * @Bean public JdbcBatchItemWriter<Items> writer() { JdbcBatchItemWriter<Items>
	 * itemWriter = new JdbcBatchItemWriter<>();
	 * itemWriter.setDataSource(dataSource);
	 * itemWriter.setSql("INSERT INTO items (item_desc,item_name,price,vendor_id)" +
	 * " VALUES (:itemDesc, :itemName ,:description, :price,:vid)");
	 * itemWriter.setItemSqlParameterSourceProvider(new
	 * BeanPropertyItemSqlParameterSourceProvider<Items>()); return itemWriter; }
	 */
	
	
}