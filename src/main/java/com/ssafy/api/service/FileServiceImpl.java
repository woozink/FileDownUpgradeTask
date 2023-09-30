    package com.ssafy.api.service;

    import software.amazon.awssdk.core.sync.ResponseTransformer;
    import software.amazon.awssdk.regions.Region;
    import software.amazon.awssdk.services.s3.S3Client;
    import software.amazon.awssdk.services.s3.model.GetObjectRequest;


    import com.ssafy.common.error.ErrorCode;
    import com.ssafy.common.exception.CustomException;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Service;

    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.net.URI;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.List;
    import java.util.zip.ZipEntry;
    import java.util.zip.ZipOutputStream;

    //TODO API를 이용한 구현
    //@Service
    //public class FileServiceImpl implements FileService {
    //    //application.properties에 app.upload.dir을 정의하고, 없는 경우에는 default값으로 user.home
    //    @Value("${app.upload.dir:${user.home}}")
    //    private String DOMAIN;
    //    private String uploadPath;
    //
    //    public String fileDownload(String fileName) {
    //        uploadPath = DOMAIN + File.separator + "files" + "/" + fileName;
    //        return uploadPath;
    //
    //    }
    //}

    //TODO S3를 이용한 다운로드 구현
    @Service
    public class FileServiceImpl implements FileService {

        @Value("${aws.s3.endpoint}")
        private String s3Endpoint;

        @Value("${aws.s3.bucket}")
        private String s3Bucket;

        @Value("${app.upload.dir:${user.home}}")
        private String DOMAIN;
        private String uploadPath;

        public File fileDownload(String fileName) {
            S3Client s3Client = S3Client.builder()
                    .endpointOverride(URI.create(s3Endpoint))
                    .region(Region.US_EAST_1)
                    .build();

            try {
                Path downloadPath = Paths.get(DOMAIN, "files", fileName);
                s3Client.getObject(GetObjectRequest.builder().bucket(s3Bucket).key(fileName).build(),
                        ResponseTransformer.toFile(downloadPath));

                return downloadPath.toFile();
            } finally {
                s3Client.close();
            }
        }
    }