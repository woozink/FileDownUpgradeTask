package com.ssafy.api.controller;

import com.ssafy.api.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/download")
public class FileDownloadController {

    private final FileDownloadService fileDownloadService;

    @Autowired
    public FileDownloadController(FileDownloadService fileDownloadService) {
        this.fileDownloadService = fileDownloadService;
    }
    // -> s3 커스텀
    @GetMapping("/folder")
    public ResponseEntity<String> downloadFolder(@RequestParam String project) {
        fileDownloadService.downloadFolder(project);
        return ResponseEntity.ok("Folder download initiated for project: " + project);
    }
}

