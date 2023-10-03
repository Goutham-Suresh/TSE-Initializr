package com.examplezip.demozip.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Dependency")
public class Dependency {

	@Id
    private String Id;
	private String Maven;
    private String Gradle;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getMaven() {
		return Maven;
	}
	public void setMaven(String maven) {
		Maven = maven;
	}
	public String getGradle() {
		return Gradle;
	}
	public void setGradle(String gradle) {
		Gradle = gradle;
	}
	public Dependency(String id, String maven, String gradle) {
		super();
		Id = id;
		Maven = maven;
		Gradle = gradle;
	}
	public Dependency() {
		super();
	}
	  
}
