package com.santahat.controller;

import com.santahat.bean.Face;
import com.santahat.bean.Hat;
import com.santahat.service.PictureService;
import com.santahat.service.impl.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * main controller of santaHat application
 */
@Controller

public class MainController {

    @Resource
    private PictureService pictureSer;

    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        File image = pictureSer.uploadFile(file,request);
        List<Face> faces = pictureSer.findFaces("/img/fileUpload/"+file.getOriginalFilename());
        List<Hat> hats = pictureSer.chooseSantaHat(faces);
        String picture = pictureSer.mergeImage(image,hats,request);
        request.getSession().setAttribute("url",picture);
        return "result";
    }

}
