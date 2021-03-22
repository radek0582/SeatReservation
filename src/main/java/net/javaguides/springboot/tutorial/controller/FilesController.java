package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/files")
@Controller
public class FilesController {
    @Autowired
    FileService fileServiceImpl;

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile (@PathVariable("filename") String fileName){
        Resource file = fileServiceImpl.getFileAsResource(fileName);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+ file.getFilename()+"\"")
                .body(file);
    }
}
