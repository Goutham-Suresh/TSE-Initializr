package com.examplezip.demozip.service.impl;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.examplezip.demozip.model.Details;
import com.examplezip.demozip.service.RuntimeClass;
@Service
public class RuntimeClassImpl implements RuntimeClass {

	@Override
	public void createClass(Details detail) {

		String name=detail.getArtifact();
		String cname=detail.getName();
		 String firstLetter = cname.substring(0, 1);
		    String remainingLetters = cname.substring(1, name.length());

		    firstLetter = firstLetter.toUpperCase();

		    cname = firstLetter + remainingLetters;
		
		boolean isWar=false;
		
		 String pack=detail.getPackage_name();
		 String[] sub_pack=pack.split("[.]");
		 String path="D:\\demo\\"+name+"\\src\\main\\java";
		 for(String pck:sub_pack) {
			 path=path+"\\"+pck;
		 }
		// System.out.println(path);
		// String name="demo";
		 
		try {
			FileWriter myWriter = new FileWriter(path+"\\"+cname+"Application.java");
			 myWriter.write("package "+pack+";\r\n"
				 		+ "\r\n"
				 		+ "import org.springframework.boot.SpringApplication;\r\n"
				 		+ "import org.springframework.boot.autoconfigure.SpringBootApplication;\r\n"
				 		+ "\r\n"
				 		+ "@SpringBootApplication\r\n"
				 		+ "public class "+ cname+"Application {\r\n"
				 		+ "\r\n"
				 		+ "	public static void main(String[] args) {\r\n"
				 		+ "		SpringApplication.run("+cname+"Application.class, args);\r\n"
				 		+ "	}\r\n"
				 		+ "\r\n"
				 		+ "}\r\n"
				 		+ "");
			 myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("not worked");
			e.printStackTrace();
		}
		  String path2="D:\\demo\\"+name+"\\src\\test\\java";
		 for(String pck:sub_pack) {
			 path2=path2+"\\"+pck;
		 }
		// System.out.println(path);
		
		try {
			FileWriter myWriter = new FileWriter(path2+"\\"+cname+"ApplicationTests.java");
			 myWriter.write("package "+pack+";\r\n"
				 		+ "\r\n"
				 		+ "import org.junit.jupiter.api.Test;\r\n"
				 		+ "import org.springframework.boot.test.context.SpringBootTest;\r\n"
				 		+ "\r\n"
				 		+ "@SpringBootTest\r\n"
				 		+ "class "+cname+"ApplicationTests {\r\n"
				 		+ "\r\n"
				 		+ "	@Test\r\n"
				 		+ "	void contextLoads() {\r\n"
				 		+ "	}\r\n"
				 		+ "\r\n"
				 		+ "}");
			
			 myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("not worked");
			e.printStackTrace();
		}
		if(detail.getPackaging().equals("War")) {
			try {
				FileWriter myWriter = new FileWriter(path+"\\ServletInitializer.java");
				 myWriter.write("package "+pack+";\r\n"
				 		+ "\r\n"
				 		+ "import org.springframework.boot.builder.SpringApplicationBuilder;\r\n"
				 		+ "import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;\r\n"
				 		+ "\r\n"
				 		+ "public class ServletInitializer extends SpringBootServletInitializer {\r\n"
				 		+ "\r\n"
				 		+ "	@Override\r\n"
				 		+ "	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {\r\n"
				 		+ "		return application.sources("+cname+"Application.class);\r\n"
				 		+ "	}\r\n"
				 		+ "\r\n"
				 		+ "}\r\n"
				 		+ "");
				 myWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("not worked");
				e.printStackTrace();
			}
		}
		
	}

}
