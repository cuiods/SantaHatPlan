package com.santahat.service.impl;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.santahat.bean.Face;
import com.santahat.bean.Hat;
import com.santahat.service.PictureService;
import com.santahat.util.APIManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
            System.out.println(jsonResponse.getBody());
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
    public Hat chooseSantaHat(Face face) {
        return null;
    }
}
