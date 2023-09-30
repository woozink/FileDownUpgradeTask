package com.ssafy.api.controller;

import com.ssafy.api.service.FileService2;
import com.ssafy.common.util.ApiResponse;
import com.ssafy.common.util.ApiResponseGenerator;
import com.ssafy.common.util.ResponseEntityGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
@RestController
public class FileController2 {

    private final FileService2 fileService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(final @RequestParam String fileName) {
        byte[] fileContent = fileService.download(fileName);
        return ResponseEntityGenerator.of(fileContent, fileName); // 유틸리티 클래스의 메서드 호출
    }

    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file) {
        return ApiResponseGenerator.of(fileService.upload(file));
    }

}
