package com.examplezip.demozip.service.impl;
import com.examplezip.demozip.model.Dependency;
import com.examplezip.demozip.model.Details;
import com.examplezip.demozip.repository.DependencyRepsitory;
import com.examplezip.demozip.service.gradleService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
@Service
public class GradleServiceImpl implements gradleService{

	@Autowired
	  DependencyRepsitory dependencyRepository;
	@Override
	public void createGradle( Details detail) {
//	    File myObj = new File("D:\\demo\\build.gradle");
	     
	      List<String> list=detail.getDependencies();
	      
	      
	      try {
				 
		      FileWriter myWriter = new FileWriter("D:\\demo\\"+detail.getArtifact()+"\\build.gradle");
		      myWriter.write("plugins {\r\n"
		    		+ "  id 'java'\r\n"
		      		+ "  id 'org.springframework.boot' version '"+ detail.getSpring()+"'\r\n"
		      		+ "  id 'io.spring.dependency-management' version '1.1.3'\r\n");
		      if(detail.getPackaging().equals("War")) {
		    	  myWriter.write(" id 'war'\r\n");
		      }
		      myWriter.write("}\r\n");
		      
		      myWriter.write("group = '" + detail.getGroup() + "'\r\n"
		    	        + "version = '0.0.1-SNAPSHOT'\r\n"
		    	        + "java {\r\n"
		    	        + "    sourceCompatibility = '" + detail.getJava() + "'\r\n"
		    	        + "}\r\n\r\n");
		      
		      myWriter.write("repositories {\r\n"
						+ "  mavenCentral()\r\n"
						+ "}\r\n"
						);
		      
		      myWriter.write("dependencies {\r\n");
		      boolean web=false;
		      boolean jpa=false;
				for(String str1: list ) {
					//System.out.println(str1);
					if(str1.equals("Spring Web")) {
						web=true;
					}
					if(str1.equals("Spring Data JPA")) {
						jpa=true;
					}
					Optional<Dependency> opt=dependencyRepository.findById(str1);
	            	
	            	if(opt.isEmpty()) {
	            		
	            		continue;
	            	}
	            	
	            	//System.out.println(opt.get().getGradle());
	            	String str=opt.get().getGradle();
					myWriter.write(str + "\n");
				}
				if(!web&&!jpa) {
					if(detail.getPackaging().equals("War"))
					myWriter.write("implementation 'org.springframework.boot:spring-boot-starter-web'"+ "\n");
					else 
						myWriter.write("implementation 'org.springframework.boot:spring-boot-starter'"+ "\n");
				}
		      if(detail.getPackaging().equals("War")) {
		    	  myWriter.write("providedRuntime 'org.springframework.boot:spring-boot-starter-tomcat'"+ "\n");
		      }
		      myWriter.write("testImplementation 'org.springframework.boot:spring-boot-starter-test'"+ "\n");
		      
				myWriter.write("}\r\n"
						 + "\n");
				
				myWriter.write("tasks.named('test') {\r\n"
						+ "  useJUnitPlatform()\r\n"
						+ "}");
		      myWriter.close();
          
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}

}
