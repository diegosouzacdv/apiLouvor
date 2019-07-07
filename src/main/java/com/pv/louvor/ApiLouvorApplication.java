package com.pv.louvor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pv.louvor.services.S3Services;

@SpringBootApplication
public class ApiLouvorApplication implements CommandLineRunner{
	

@Autowired
private S3Services s3;

	public static void main(String[] args) {
		SpringApplication.run(ApiLouvorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3.uploadFile("C:\\teste\\diego.jpg");
}
}
