package com.zVelto.cursospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zVelto.cursospring.domain.services.S3Service;

@SpringBootApplication
public class CursoSpringApplication implements CommandLineRunner{
	
	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("C:\\Users\\welton\\Pictures\\op.jpg");	
	}

}
