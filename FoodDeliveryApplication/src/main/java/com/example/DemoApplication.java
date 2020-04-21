package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
@EnableSwagger2
public class DemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	@Bean
    public ThreadPoolTaskExecutor taskExecutor() {
	    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	    taskExecutor.setCorePoolSize(15);
	    taskExecutor.setMaxPoolSize(20);
	    taskExecutor.setQueueCapacity(30);
	    return taskExecutor;
	}
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example")).build();
    }
	
	/*
	 * @Bean public JobLauncher jobLauncher(ThreadPoolTaskExecutor taskExecutor,
	 * JobRepository jobRepository){ SimpleJobLauncher jobLauncher = new
	 * SimpleJobLauncher(); jobLauncher.setTaskExecutor(taskExecutor);
	 * jobLauncher.setJobRepository(jobRepository); return jobLauncher; }
	 */
}