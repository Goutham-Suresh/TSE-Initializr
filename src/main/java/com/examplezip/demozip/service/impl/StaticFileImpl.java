package com.examplezip.demozip.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.examplezip.demozip.model.Details;
import com.examplezip.demozip.service.StaticFile;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSDownloadOptions;

@Service
public class StaticFileImpl implements StaticFile{

	@Override
	public void createFiles(Details detail) {
		
		if(detail.getProject().equals("Maven")) {
			 
			 String project_name=detail.getArtifact();
				File parent = new File("D:/demo/"+project_name+"/.mvn/wrapper");
				parent.mkdirs();		 
			    
			     String path ="D:\\demo\\"+project_name;
					
			     String uri = "mongodb://localhost:27017";
			     // Create a MongoDB Client
			     MongoClient mongoClient = MongoClients.create(uri);
			     
			     MongoDatabase database = mongoClient.getDatabase("mavenDB");
			     GridFSBucket gridFSBucket = GridFSBuckets.create(database);
			     
			         GridFSDownloadOptions downloadOptions = new GridFSDownloadOptions().revision(0);
			         {
			         try {
			        	 
			        	 System.out.println("Starting Maven File download");
			        	 
			        	 FileOutputStream streamToDownloadTo = new FileOutputStream(path+"\\.mvn\\wrapper\\" +  "maven-wrapper.jar");
			             gridFSBucket.downloadToStream("maven-wrapper.jar", streamToDownloadTo, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo1 = new FileOutputStream(path+"\\.mvn\\wrapper\\" +   "maven-wrapper.properties");
			             gridFSBucket.downloadToStream("maven-wrapper.properties", streamToDownloadTo1, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo2 = new FileOutputStream(path+"\\" +   "HELP.md");
			             gridFSBucket.downloadToStream("HELP.md", streamToDownloadTo2, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo3 = new FileOutputStream(path+"\\" +  "mvnw");
			             gridFSBucket.downloadToStream("mvnw", streamToDownloadTo3, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo4 = new FileOutputStream(path+"\\" +  "mvnw.cmd");
			             gridFSBucket.downloadToStream("mvnw.cmd", streamToDownloadTo4, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo5 = new FileOutputStream(path+"\\" +  ".gitignore");
			             gridFSBucket.downloadToStream(".gitignore", streamToDownloadTo4, downloadOptions);
			             
			             
			             streamToDownloadTo.flush();
			             
			             System.out.println("ALL Static Maven files downloaded!");
			             
			         } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
			        	 System.out.println("maven file retrieval error");
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		
	}
		else {
			 String project_name=detail.getArtifact();
				File parent = new File("D:/demo/"+project_name+"/gradle/wrapper");
				parent.mkdirs();		 

			    
			     String path ="D:\\demo\\"+project_name;
			    // String path ="D:\\demo";
					
			     String uri = "mongodb://localhost:27017";
			     // Create a MongoDB Client
			     MongoClient mongoClient = MongoClients.create(uri);
			     
			     MongoDatabase database = mongoClient.getDatabase("gradleDB");
			     GridFSBucket gridFSBucket = GridFSBuckets.create(database);
			     
			         GridFSDownloadOptions downloadOptions = new GridFSDownloadOptions().revision(0);
			         {
			         try {
			        	 
			        	 System.out.println("Starting Gradle File download");
			        	 
			        	 FileOutputStream streamToDownloadTo = new FileOutputStream(path+"\\gradle\\wrapper\\" +  "gradle-wrapper.jar");
			             gridFSBucket.downloadToStream("gradle-wrapper.jar", streamToDownloadTo, downloadOptions);
			             FileOutputStream streamToDownloadTo1 = new FileOutputStream(path+"\\gradle\\wrapper\\" +   "gradle-wrapper.properties");
			             gridFSBucket.downloadToStream("gradle-wrapper.properties", streamToDownloadTo1, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo2 = new FileOutputStream(path+"\\" +   "HELP.md");
			             gridFSBucket.downloadToStream("HELP.md", streamToDownloadTo2, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo3 = new FileOutputStream(path+"\\" +  "setting.gradle");
			             gridFSBucket.downloadToStream("settings.gradle", streamToDownloadTo3, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo4 = new FileOutputStream(path+"\\" +  "gradlew.bat");
			             gridFSBucket.downloadToStream("gradlew.bat", streamToDownloadTo4, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo5 = new FileOutputStream(path+"\\" +  "gradlew");
			             gridFSBucket.downloadToStream("gradlew", streamToDownloadTo4, downloadOptions);
			             
			             FileOutputStream streamToDownloadTo6 = new FileOutputStream(path+"\\" +  ".gitignore");
			             gridFSBucket.downloadToStream(".gitignore", streamToDownloadTo4, downloadOptions);
			             
			             streamToDownloadTo.flush();
			             System.out.println("ALL Static Gradle files downloaded!");
			             
			         } catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			         }
		}

}}
