package com.ssafy.common.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.amazonaws.AmazonServiceException;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Util {
    private final AmazonS3 s3Client; // 여기서 AmazonS3Client를 AmazonS3로 변경합니다.
    private static final int TIMEOUT = 5;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    @MeasureExecutionTime
    public void downloadFolder(String project) {
        try {
            project = URLDecoder.decode(project, StandardCharsets.UTF_8.name());
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            System.out.println(""+Runtime.getRuntime().availableProcessors()); // ?

            ListObjectsV2Result result = s3Client.listObjectsV2(bucket, project);
            for (S3ObjectSummary summary: result.getObjectSummaries()) {
                GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, summary.getKey());
                executor.submit(() -> {
                    s3Client.getObject(getObjectRequest, new File(summary.getKey()));
                });
            }
            executor.shutdown();
            executor.awaitTermination(TIMEOUT, TimeUnit.MINUTES);
        } catch (AmazonServiceException e) {
            log.error("Amazon service exception: ", e);
        } catch (InterruptedException e) {
            log.error("awaitTermination exception: ", e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
