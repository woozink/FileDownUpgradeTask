//package com.ssafy.api.controller;
//
//import com.ssafy.api.service.FileServiceImpl;
//import com.ssafy.common.error.ErrorCode;
//import com.ssafy.common.exception.CustomException;
//import io.swagger.annotations.Api;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import org.springframework.http.HttpHeaders;
//
///**
// * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
// */
//@Api(value = "파일 API", tags = {"File"})
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/file")
//public class FileController {
//    private final FileServiceImpl fileServiceImpl;
//
//    //TODO API전용
////    @GetMapping("/download/{filename}")
////    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException {
////        Resource resource = new UrlResource("file:" + fileServiceImpl.fileDownload(filename));
////        if(!resource.isReadable())
////            throw new CustomException(ErrorCode.FILE_NOT_FOUND);
////
////        HttpHeaders headers = new HttpHeaders();
////        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());
////
////        return ResponseEntity.ok()
////                .headers(headers)
////                .body(resource);
////    }
//
//    //TODO S3
//    @GetMapping("/download/{filename}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException {
//        File file = fileServiceImpl.fileDownload(filename);
//        Resource resource = new UrlResource(file.toURI());
//        if(!resource.isReadable())
//            throw new CustomException(ErrorCode.FILE_NOT_FOUND);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(resource);
//    }
//
//}
