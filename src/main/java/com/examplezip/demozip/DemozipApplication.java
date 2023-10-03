package com.examplezip.demozip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import io.spring.initializr.metadata.InitializrMetadata;
import io.spring.initializr.metadata.InitializrMetadataBuilder;
import io.spring.initializr.metadata.InitializrMetadataProvider;
import io.spring.initializr.metadata.InitializrProperties;
import io.spring.initializr.metadata.SimpleInitializrMetadataProvider;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemozipApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemozipApplication.class, args);
		
	}
	
	@Bean
	public InitializrMetadataProvider initializrMetadataProvider(
	        InitializrProperties properties) {
	    InitializrMetadata metadata = InitializrMetadataBuilder.fromInitializrProperties(properties).build();
	    return new SimpleInitializrMetadataProvider(metadata);
	}

}
