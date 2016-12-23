package com.santahat.controller;

import com.santahat.service.PictureService;
import com.santahat.service.impl.PictureServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * main controller of santaHat application
 */
@Controller

public class MainController {
    private PictureService pictureSer = new PictureServiceImpl();

    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        return pictureSer.uploadFile(file);
    }

}
