package com.santahat.service.impl;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.santahat.bean.Face;
import com.santahat.bean.Hat;
import com.santahat.service.PictureService;
import com.santahat.util.APIManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toList;

/**
 * implement of picture service
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Resource
    private APIManager apiManager;

    @Override
    public List<Face> findFaces(String url) {
        System.out.println("start find...");
        url = apiManager.getPreffix()+url;
        List<Face> faces = new ArrayList<Face>();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url",url);
            com.mashape.unirest.http.HttpResponse<JsonNode> jsonResponse = Unirest.post(apiManager.getFaceDetectApi())
                    .header("Content-Type", "application/json")
                    .header("Ocp-Apim-Subscription-Key", apiManager.getFaceToken())
                    .body(jsonObject)
                    .asJson();
            System.out.println("body:"+jsonResponse.getBody());
            JSONArray jsonArray = jsonResponse.getBody().getArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                JSONObject rec = object.getJSONObject("faceRectangle");
                Face face = new Face(rec.getInt("width"),rec.getInt("height"),rec.getInt("left"),rec.getInt("top"));
                faces.add(face);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return faces;
    }

    @Override
    public List<Hat> chooseSantaHat(List<Face> faces) {
        return faces.stream()
                .map(this::chooseSantaHat).collect(toList());
    }

    @Override
    public String mergeImage(File image, List<Hat> hats, HttpServletRequest request) {
        if (image == null) {
            return "";
        }
        try {
            BufferedImage result = ImageIO.read(image);
            Graphics graphics = result.getGraphics();
            for (Hat hat : hats) {
                String filePath = "";
                if (request!=null) {
                    filePath=request.getSession().getServletContext().getRealPath("/img/hats/");
                }
                BufferedImage hatImage = ImageIO.read(new File(filePath,hat.getHat_url()));
                graphics.drawImage(hatImage,hat.getLeft(),(int)(hat.getTop()-hat.getHeight()*1.2),
                        hat.getWidth(),hat.getHeight(),null);
            }
            ImageIO.write(result,"PNG",image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/img/fileUpload/"+image.getName();
    }

    @Override
    public Hat chooseSantaHat(Face face) {
//        String url = apiManager.getHatUrl();
        String url = "xmas_9.png";
        Hat hat = new Hat(face);
        hat.setHat_url(url);
        return hat;
    }

    @Override
    public File uploadFile(MultipartFile file,HttpServletRequest request) {
        if(!file.isEmpty()){
            String picUrl = file.getOriginalFilename();
            String filePath = request.getSession().getServletContext().getRealPath("/img/fileUpload/");
            System.out.println(filePath+picUrl);
            try {
                File image = new File(filePath,picUrl);
                file.transferTo(image);
                return image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
