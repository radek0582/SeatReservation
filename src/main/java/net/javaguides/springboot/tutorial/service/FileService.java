package net.javaguides.springboot.tutorial.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface FileService {
    String store (MultipartFile file) throws IOException, FileServiceException;
    Resource getFileAsResource(String filePath);
}
