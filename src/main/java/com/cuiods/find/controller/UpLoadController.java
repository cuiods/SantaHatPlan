package com.cuiods.find.controller;

import com.cuiods.find.model.APIManager;
import com.cuiods.find.model.PicDescription;
import com.cuiods.find.model.UpLoadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * upload and save a picture.
 */
@Controller
@RequestMapping("/upload")
public class UpLoadController {

    @Autowired
    private UpLoadModel loadModel;

    @Autowired
    private PicDescription description;

    @Autowired
    private APIManager manager;

    @RequestMapping(value = "/web")
    String webUploadPicture(@RequestParam(value = "file", required = false) MultipartFile file,
                            HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("temp");
        String fileName = loadModel.webUploadPicture(file,path,request);
        request.getSession().setAttribute("fileUrl",fileName);
        return "result";
    }
}
