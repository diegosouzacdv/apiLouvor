package com.pv.louvor.services;

import org.springframework.stereotype.Service;

@Service
public class S3Services {
	
//	private Logger LOG = LoggerFactory.getLogger(S3Services.class);
//	
//	@Autowired
//	private AmazonS3 s3client;
//	
//	@Value("${s3.bucket}")
//	private String bucketName;
//	
//	public URI uploadFile(MultipartFile multipartFile) {
//		try {
//				String fileName = multipartFile.getOriginalFilename();
//				InputStream is;
//				is = multipartFile.getInputStream();
//				String contentType = multipartFile.getContentType();
//				return uploadFile(is, fileName, contentType);
//			} catch (IOException e) {
//				throw new FileException("Error de IO: " + e.getMessage());
//			}
//	}
//	
//	public URI uploadFile(InputStream is, String fileName, String contentType) {
//		ObjectMetadata meta = new ObjectMetadata();
//		meta.setContentType(contentType);
//		LOG.info("Iniciando uplaod");
//		s3client.putObject(bucketName, fileName, is, meta);
//		LOG.info("Upload Finalizado");
//		try {
//			return s3client.getUrl(bucketName, fileName).toURI();
//		} catch (URISyntaxException e) {
//			throw new FileException("Error ao converter URL para URI");
//		}
//	}
	

}
