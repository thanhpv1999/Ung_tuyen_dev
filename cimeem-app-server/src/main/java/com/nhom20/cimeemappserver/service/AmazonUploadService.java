package com.nhom20.cimeemappserver.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.nhom20.cimeemappserver.entity.File3S;



@Service
public class AmazonUploadService {

    private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Value("${amazonProperties.accessKey}")
    private String accessKey;

    @Value("${amazonProperties.secretKey}")
    private String secretKey;

	@PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = new AmazonS3Client(credentials);
	}
	
	private void uploadFileTos3bucket(String fileName, File file) {
	    s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
	            .withCannedAcl(CannedAccessControlList.PublicRead));
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
	private String generateFileName(MultipartFile multiPart) {
	    return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}
	
	public String uploadFile(MultipartFile multipartFile) {

	    String fileUrl = "";
	    try {
	        File file = convertMultiPartToFile(multipartFile);
	        String fileName = generateFileName(multipartFile);
	        fileUrl = "https://"+ bucketName + ".s3.amazonaws.com/" + fileName;
	        uploadFileTos3bucket(fileName, file);
	        file.delete();
	    } catch (Exception e) {
	       e.printStackTrace();
	    }
	    return fileUrl;
	}
	
	public List<File3S> listFile() {
		List<File3S> listFile = new ArrayList<File3S>();
		try {
			String fileName, linkDownload;
            System.out.println("Listing objects");
            final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName).withMaxKeys(4);
            ListObjectsV2Result result;
            do {               
               result = s3client.listObjectsV2(req);
               
               for (S3ObjectSummary objectSummary : 
                   result.getObjectSummaries()) {
                   System.out.println(" - " + objectSummary.getKey() + "  " +
                           "(size = " + objectSummary.getSize() + 
                           ")");
                   fileName = objectSummary.getKey();
                   linkDownload = "https://"+ bucketName + ".s3.amazonaws.com/" + fileName;
                   File3S s3File = new File3S(fileName, linkDownload);
                   listFile.add(s3File);
               }
               req.setContinuationToken(result.getNextContinuationToken());
            } while(result.isTruncated() == true ); 
            
         } catch (AmazonServiceException ase) {
            
        } catch (AmazonClientException ace) {
            
            System.out.println("Error Message: " + ace.getMessage());
        }
		if(listFile.size() == 0) {
			return null;
		}
		return listFile;
	}
}