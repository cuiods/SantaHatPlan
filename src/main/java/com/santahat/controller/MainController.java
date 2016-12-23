package com.santahat.controller;

import com.santahat.service.PictureService;
import com.santahat.service.impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * main controller of santaHat application
 */
@Controller

public class MainController {
    @Autowired
    private HttpServletRequest request;

    private PictureService pictureSer = new PictureServiceImpl();

    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file){
        pictureSer.uploadFile(file,request);
        return "result";
    }

}
