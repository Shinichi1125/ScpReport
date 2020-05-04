package com.example.demo.controller;

import com.example.demo.model.DBFile;
import com.example.demo.payload.UploadFileResponse;
import com.example.demo.service.DBFileStorageService;

import java.nio.file.Paths;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
import java.nio.file.Path;

import org.springframework.web.servlet.ModelAndView;

@RestController
public class FileController {
	
	// destination folder to upload the files 
	private static String UPLOAD_FOLDER = "C://spring_img//";

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService dbFileStorageService;

    // necessary for the "upload" to store an image to the designated path
    @RequestMapping("/uploadFile")
    public ModelAndView showUpload() {
    	return new ModelAndView("uploadFile");
    }
    
  //UPLOADING THE FILE AND DISPLAYING A DOWNLOAD URL	
//    @PostMapping("/uploadFile")
//    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
//    	   
//        DBFile dbFile = dbFileStorageService.storeFile(file);
//        
//        // dbFile.getId() returns an int type, but path() requires a string type
//        // because an attempt to cast the return value of dbFile.getId() to String didn't work,
//        // try to concatenate the integer to an empty string to convert it to String 
//        String strId = "" + dbFile.getId();
//
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/downloadFile/")
//                .path(/*dbFile.getId()*/ strId)
//                .toUriString();
//
//        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
//                file.getContentType(), file.getSize());
//    }

    @PostMapping("/uploadFile")
    public ModelAndView fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    	if(file.isEmpty()) {
    		return new ModelAndView("status", "message", "Please select a file and try again");
    	}
    	
    	try {
    		// read and write the file to the selected location
    		byte[] bytes = file.getBytes();
    		Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
    		Files.write(path, bytes);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return new ModelAndView("status", "message", "File Uploaded sucessfully");
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