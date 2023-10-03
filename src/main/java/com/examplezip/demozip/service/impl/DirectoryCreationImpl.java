package com.examplezip.demozip.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examplezip.demozip.model.Details;


import com.examplezip.demozip.service.DirectoryCreation;
@Service
public class DirectoryCreationImpl implements DirectoryCreation {


	@Override
	public void create(Details detail) {

		
		String package_name = detail.getPackage_name();
		String project_name=detail.getArtifact();
		String[] pn = package_name.split("[.]");
		
		//clear directory
		File directory = new File("D:/demo");
		clearDirectoryContents(directory);
		
		File parent = new File("D:/demo"+File.separator+project_name);
		parent.mkdirs(); //create directory
		
		File sub_parent = new File(parent,"src");
		sub_parent.mkdirs();
		String path = "D:/demo/" + project_name + "/" + "src";

		String subs[] = {"main","test"};

		for (String sub : subs) {
			File subFile = new File(path, sub);
			subFile.mkdirs();

		}
		File subFile1 = new File("D:/demo/" + project_name + "/" + "src/main", "java");
		subFile1.mkdirs();
		subFile1 = new File("D:/demo/" + project_name + "/" + "src/main", "resources");
		subFile1.mkdirs();
		try {
			FileWriter myWriter = new FileWriter("D:\\demo\\"+project_name+"\\src\\main\\resources\\application.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subFile1 = new File("D:/demo/" + project_name + "/" + "src/test", "java");
		subFile1.mkdirs();

		String path2="D:/demo/" + project_name + "/" + "src/main/java";
		for(String s:pn) {
			File subFile = new File(path2, s);
			subFile.mkdirs();
			path2=path2+"/"+s;

		}
		String path3="D:/demo/" + project_name + "/" + "src/test/java";
		for(String s:pn) {
			File subFile = new File(path3, s);
			subFile.mkdirs();
			path3=path3+"/"+s;

		}

	}
	
	private void clearDirectoryContents(File directory) {
	    if (directory.exists() && directory.isDirectory()) {
	        File[] files = directory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    clearDirectoryContents(file); // Recursively clear subdirectories
	                } else {
	                    file.delete();
	                }
	            }
	        }
	    }
	}
	
}