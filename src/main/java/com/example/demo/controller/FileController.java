package com.example.demo.controller;

import com.example.demo.model.DBFile;
import com.example.demo.payload.UploadFileResponse;
import com.example.demo.service.DBFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService dbFileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        DBFile dbFile = dbFileStorageService.storeFile(file);
        
        // dbFile.getId() returns an int type, but path() requires a string type
        // because an attempt to cast the return value of dbFile.getId() to String didn't work,
        // try to concatenate the integer to an empty string to convert it to String 
        String strId = "" + dbFile.getId();

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(/*dbFile.getId()*/ strId)
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable /*String*/ int fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}