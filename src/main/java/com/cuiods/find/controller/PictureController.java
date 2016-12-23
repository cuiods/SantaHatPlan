package com.cuiods.find.controller;

import com.cuiods.find.entity.Description;
import com.cuiods.find.entity.Face;
import com.cuiods.find.model.FindFace;
import com.cuiods.find.model.PicDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by cuihao on 2016/8/19.
 */
@Controller
@RequestMapping("/pic")
public class PictureController {

    @Autowired
    private PicDescription picDescription;

    @Autowired
    private FindFace findFace;

    @RequestMapping("/description")
    @ResponseBody
    public Description getDescription(@RequestParam("url") String url) {
        return picDescription.getPicDescriptionByUrl(url);
    }

    @RequestMapping("/detect")
    @ResponseBody
    public List<Face> getFace(@RequestParam("url") String url) {
        return findFace.detectFaces(url);
    }

    @RequestMapping("/identify")
    @ResponseBody
    public Face indentify(@RequestParam("url") String url) {
        return findFace.identifyFaces(findFace.detectFaces(url));
    }
}
