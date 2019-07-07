package com.pv.louvor.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Services {
	
	private Logger LOG = LoggerFactory.getLogger(S3Services.class);
	
	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public URI uploadFile(MultipartFile multipartFile) {
		try {
				String fileName = multipartFile.getOriginalFilename();
				InputStream is;
				is = multipartFile.getInputStream();
				String contentType = multipartFile.getContentType();
				return uploadFile(is, fileName, contentType);
			} catch (IOException e) {
				throw new RuntimeException("Error de IO: " + e.getMessage());
			}
	}
	
	public URI uploadFile(InputStream is, String fileName, String contentType) {
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType(contentType);
		LOG.info("Iniciando uplaod");
		s3client.putObject(bucketName, fileName, is, meta);
		LOG.info("Upload Finalizado");
		try {
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Error ao converter URL para URI");
		}
	}
	

}
