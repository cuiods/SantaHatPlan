package com.cuiods.find.model;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by cuihao on 2016/8/17.
 */
@Component
public class UpLoadModel {

    public String webUploadPicture(MultipartFile file, String path, HttpServletRequest request) {
        String fileName = file.getOriginalFilename().replace(" ","");
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }

}
