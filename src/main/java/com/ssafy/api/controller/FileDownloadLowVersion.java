package com.ssafy.api.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RestController
public class FileDownloadLowVersion {
    @GetMapping("/download")
    public ResponseEntity<StreamingResponseBody> downloadFile() throws FileNotFoundException {
        File file = new File("/Users/getout/Desktop/files/runtopia2.zip");
        InputStream inputStream = new FileInputStream(file);
        StreamingResponseBody responseBody = outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
        System.out.println("*주소 주소 주소*"+ file);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file.zip\"")
                .body(responseBody);
    }
}
