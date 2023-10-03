package com.examplezip.demozip.controller;

import com.examplezip.demozip.model.Details;
import com.examplezip.demozip.service.DirectoryCreation;
import com.examplezip.demozip.service.DownloadService;
import com.examplezip.demozip.service.RuntimeClass;
import com.examplezip.demozip.service.StaticFile;
import com.examplezip.demozip.service.gradleService;
import com.examplezip.demozip.service.pomService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DownloadController {
    private Logger logger = LoggerFactory.getLogger(DownloadController.class);

    @Autowired
    private DownloadService downloadService;
    @Autowired
    private pomService pomservice;
    @Autowired
    private DirectoryCreation directoryService;
    @Autowired
    private gradleService gradleservice;
    @Autowired
    private RuntimeClass runtimeService;
    @Autowired
    private StaticFile staticFileService;

	static String filename;
	
    @GetMapping("/tse")
	public String showForm(Model model) {
		Details detail = new Details();
		model.addAttribute("detail", detail);
		List<String> dependencies = Arrays.asList("Spring Web", "MySQL Driver", "Spring Data JPA", "Oracle Driver", "Spring Session");
		model.addAttribute("dependencies", dependencies);

		return "index";
	}
    
    @PostMapping("/tse")
	public String submitForm(@ModelAttribute("detail") Details detail) {
		directoryService.create(detail);
		runtimeService.createClass(detail);
		staticFileService.createFiles(detail);
//		System.out.println(detail);
		filename = detail.getArtifact();
		if(detail.getProject().equals("Gradle")) {
			gradleservice.createGradle(detail);
		}
		else {
			try {
				pomservice.pom(detail);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "register_success";
	}
	@GetMapping("/Download")
	public void downloadZipFile(HttpServletResponse response) {
		List<File> listOfFileNames = getListOfFileNames();
		downloadService.downloadZipFile(response, listOfFileNames, filename);
		

	}
	private List<File> getListOfFileNames() {
		List<File> listOfFileNames = new ArrayList<>();
//		System.out.println("Problem in COntroller");
		listOfFileNames.add(new File("D:/demo/"+filename));

		return listOfFileNames;
	}
   /* @GetMapping("/pom")
    public  ResponseEntity<String>  get() throws Exception{
    	//pomservice.pom();
    	return ResponseEntity.status(HttpStatus.CREATED).body("Pom is Created");
    	
    }
    @GetMapping("/gradle")
    public  ResponseEntity<String>  getgradle() throws Exception{
    	gradleservice.createGradle();
    	return ResponseEntity.status(HttpStatus.CREATED).body("gradle is Created");
    	
    }
    
    @GetMapping("/create")
    public void create() {
    	directoryService.create();
    }
    
    @GetMapping("/downloadZipFile")
    public void downloadZipFile(HttpServletResponse response) {
    	if(true)
    	try {
    		
			pomservice.pom();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	gradleservice.createGradle();
        List<File> listOfFileNames = getListOfFileNames();
        downloadService.downloadZipFile(response, listOfFileNames);
    }

    /**
     * List of file names for testing
     * @return
     
    private List<File> getListOfFileNames() {
        List<File> listOfFileNames = new ArrayList<>();
        listOfFileNames.add(new File("C:/demo"));
        
        return listOfFileNames;
    }*/

}
