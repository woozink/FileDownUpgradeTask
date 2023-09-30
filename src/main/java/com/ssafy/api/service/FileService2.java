package com.ssafy.api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.ssafy.common.exception.handler.FileServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService2 {

    private final AmazonS3 s3Client;

    private final String BUCKET = "runtopia-bucket";

    public byte[] download(final String fileKey) {
        S3Object fullObject = s3Client.getObject(BUCKET, fileKey);

        if (fullObject == null) {
            throw new FileServiceException("File Does Not Exist");
        }

        try {
            return IOUtils.toByteArray(fullObject.getObjectContent());
        } catch (IOException e) {
            throw new FileServiceException("Error occurred while converting object content to byte array", e);
        }
    }

    public String upload(final MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileServiceException("File is Empty");
        }

        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try {
            PutObjectRequest request = new PutObjectRequest(BUCKET, key, file.getInputStream(), metadata);
            PutObjectResult result = s3Client.putObject(request);
            return key;
        } catch (IOException e) {
            throw new FileServiceException("Error occurred while uploading the file", e);
        }
    }
}