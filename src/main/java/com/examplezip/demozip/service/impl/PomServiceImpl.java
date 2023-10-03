package com.examplezip.demozip.service.impl;


import com.examplezip.demozip.model.Dependency;
import com.examplezip.demozip.model.Details;

import com.examplezip.demozip.repository.DependencyRepsitory;

import com.examplezip.demozip.service.pomService;
import com.mongodb.DBCursor;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
@Service
public class PomServiceImpl implements pomService {
	
	@Autowired
	  DependencyRepsitory dependencyRepository;
	
	
	public void pom(Details detail)throws Exception {
		
        List<String> list=detail.getDependencies();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder buider = factory.newDocumentBuilder();
            Document doc = buider.newDocument();
            
            for (String dependency : list) {
                System.out.println(dependency);
            }


            Element project = doc.createElement("project");
            doc.appendChild(project);
            Attr attr = doc.createAttribute("xmlns1");
            attr.setValue("http://maven.apache.org/POM/4.0.0");
            project.setAttributeNode(attr);
            Attr attr1 = doc.createAttribute("xmlns:xsi");
            attr1.setValue("http://www.w3.org/2001/XMLSchema-instance");
            project.setAttributeNode(attr1);
            Attr attr2 = doc.createAttribute("xsi:schemaLocation");
            attr2.setValue("http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd");
            
            project.setAttributeNode(attr2);
            Element modelVersion = doc.createElement("modelVersion");
            modelVersion.setTextContent("4.0.0");
            project.appendChild(modelVersion);
            Element parent = doc.createElement("parent");
            project.appendChild(parent);

            Element groupId = doc.createElement("groupId");
            groupId.setTextContent("org.springframework.boot");
            parent.appendChild(groupId);

            Element artifactId = doc.createElement("artifactId");
            artifactId.setTextContent("spring-boot-starter-parent");
            parent.appendChild(artifactId);
            Element version = doc.createElement("version");
            version.setTextContent(detail.getSpring());
            parent.appendChild(version);

            Element group_Id = doc.createElement("groupId");
            group_Id.setTextContent(detail.getGroup());
            project.appendChild(group_Id);

            Element artifact_Id = doc.createElement("artifactId");
            artifact_Id.setTextContent(detail.getArtifact());
            project.appendChild(artifact_Id);

            Element version_ = doc.createElement("version");
            version_.setTextContent("0.0.1-SNAPSHOT");
            project.appendChild(version_);

            Element name = doc.createElement("name");
            name.setTextContent(detail.getName());
            project.appendChild(name);

            Element description = doc.createElement("description");
            description.setTextContent(detail.getDescription());
            project.appendChild(description);

            Element properties = doc.createElement("properties");
            project.appendChild(properties);
            Element java_version = doc.createElement("java.version");
            java_version.setTextContent(detail.getJava());
            properties.appendChild(java_version);
            Element dependencies = doc.createElement("dependencies");
            project.appendChild(dependencies);
            
            if(detail.getPackaging().equals("Jar")) {
            Element dep1 = doc.createElement("dependency");
           
            Element groupId4 = doc.createElement("groupId");
            groupId4.setTextContent("org.springframework.boot");
           dep1.appendChild(groupId4);

            Element artifactId4 = doc.createElement("artifactId");
            artifactId4.setTextContent("spring-boot-starter");
            dep1.appendChild(artifactId4);
            dependencies.appendChild(dep1);
            
            }
            else {  //WAR condition
            	if(!list.contains("Spring Web")) {
            	 Element dep7 = doc.createElement("dependency");  
                 Element groupId4 = doc.createElement("groupId");
                 groupId4.setTextContent("org.springframework.boot");
                 dep7.appendChild(groupId4);

                 Element artifactId4 = doc.createElement("artifactId");
                 artifactId4.setTextContent("spring-boot-starter-web");
                 dep7.appendChild(artifactId4);
                 dependencies.appendChild(dep7);
            	}
            	
            	Element dep3 = doc.createElement("dependency");
            	Element groupId6 = doc.createElement("groupId");
                groupId6.setTextContent("org.springframework.boot");
                dep3.appendChild(groupId6);

                 Element artifactId6 = doc.createElement("artifactId");
                 artifactId6.setTextContent("spring-boot-starter-tomcat");
                 dep3.appendChild(artifactId6);
                 Element scope1 = doc.createElement("scope");
                 scope1.setTextContent("provided");
                 dep3.appendChild(scope1);
                 dependencies.appendChild(dep3);
            }
            
            //add custom dependency conditions here
            
            Element dep4 = doc.createElement("dependency");
            
            Element groupId5 = doc.createElement("groupId");
            groupId5.setTextContent("org.springframework.boot");
           dep4.appendChild(groupId5);

            Element artifactId5 = doc.createElement("artifactId");
            artifactId5.setTextContent("spring-boot-starter-test");
            dep4.appendChild(artifactId5);
            Element scope = doc.createElement("scope");
            scope.setTextContent("test");
            dep4.appendChild(scope);
            dependencies.appendChild(dep4);
            
           
            
          
           // String[] arr=fetch.get().getDependencies();
           // List<String> arr=detail.getDependencies();
            for(int i=0;i<list.size();i++)
            {
            	Optional<Dependency> str2=dependencyRepository.findById(list.get(i));
            	
            	if(str2.isEmpty())
            		continue;
                String str=str2.get().getMaven();
               // System.out.println(str);
            String[] arr1=str.split(" ");
            //System.out.print(arr.length);
            Element dep2 = doc.createElement("dependency");
            for(int j=0;j<arr1.length-1;j=j+2){

                Element groupId3 = doc.createElement(arr1[j]);
                groupId3.setTextContent(arr1[j+1]);
                dep2.appendChild(groupId3);

            }
            dependencies.appendChild(dep2);
            }
            
            Element build = doc.createElement("build");
            project.appendChild(build);
            Element plugins = doc.createElement("plugins");
            build.appendChild(plugins);
            
                 Element plugin = doc.createElement("plugin");
                 Element groupId2 = doc.createElement("groupId");
                 groupId2.setTextContent("org.springframework.boot");
                 plugin.appendChild(groupId2);

                 Element artifactId2 = doc.createElement("artifactId");
                 artifactId2.setTextContent("spring-boot-maven-plugin");
                 plugin.appendChild(artifactId2);
                 plugins.appendChild(plugin);
            //  depi.deleteById("1");
               

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
 
            DOMSource source = new DOMSource(doc);
 
            File f = new File("D:/demo/"+detail.getArtifact()+"\\pom.xml");
            StreamResult result = new StreamResult(f);
 
            transformer.transform(source, result);
		
	}
		
	
	
	
	
}
