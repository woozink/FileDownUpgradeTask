package com.ssafy.api.service;

import com.ssafy.common.util.S3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileDownloadService {

    private final S3Util s3Util;

    @Autowired
    public FileDownloadService(S3Util s3Util) {
        this.s3Util = s3Util;
    }

    public void downloadFolder(String project) {
        s3Util.downloadFolder(project);
    }
}
