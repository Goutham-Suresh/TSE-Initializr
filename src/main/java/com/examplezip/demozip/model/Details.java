package com.examplezip.demozip.model;

import java.util.List;

public class Details {
	
	private String project;
	private String lang;
	private String spring;
	private List<String> dependencies;
	private String group;
	private String artifact;
	private String name;
	private String description;
	private String package_name;
	private String packaging;
	private String java;



	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSpring() {
		return spring;
	}

	public void setSpring(String spring) {
		this.spring = spring;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<String> dependencies) {
		this.dependencies = dependencies;
	}

	public String getGroup() {
		return group;
	}

    public void setGroup(String group) { this.group = group;}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getPackage_name() {
		return package_name;
	}

	public void setPackage_name(String package_name) {
		this.package_name = package_name;
	}


	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}


	public String getJava() {
		return java;
	}

	public void setJava(String java) {
		this.java = java;
	}


	@Override
	public String toString() {
		return "User [Project=" + project + ", Language=" + lang + ", Spring Boot=" + spring + ", Dependency= " + dependencies + ", Group =" + group + ", Artifact="
				+ artifact + ", Name=" + name + ", Description=" + description + ", Package name=" + package_name + ", Packaging=" + packaging + ", Java="
		        + java+ "]";
	}


}
