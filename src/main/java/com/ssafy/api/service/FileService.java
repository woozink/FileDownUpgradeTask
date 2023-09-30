package com.ssafy.api.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {

    //TODO API
//    String fileDownload(String fileName);

    // TODO s3
    File fileDownload(String fileName);
}
