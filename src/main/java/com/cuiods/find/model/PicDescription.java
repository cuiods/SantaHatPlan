package com.cuiods.find.model;

import com.cuiods.find.entity.Description;
import com.cuiods.find.entity.Face;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * get picture description
 * 320012161205310
 * @author cuihao
 */
@Component
public class PicDescription {

    @Autowired
    private APIManager apiManager;

    public Description getPicDescriptionByUrl(String url) {
        url = apiManager.getPreffix()+url;
        System.out.println(url);
        Description description = new Description();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url",url);
            com.mashape.unirest.http.HttpResponse<JsonNode> response = Unirest
                    .post(apiManager.getDescriptionApi()+"?visualFeatures=Description")
                    .header("Content-Type", "application/json")
                    .header("Ocp-Apim-Subscription-Key", apiManager.getDesToken())
                    .body(jsonObject.toString())
                    .asJson();
            JsonNode node = response.getBody();
            System.out.println(node);
            JSONObject result = node.getObject();
            JSONObject descriptionJson = result.getJSONObject("description");
            JSONArray tagJson = descriptionJson.getJSONArray("tags");
            List<String> tags = new ArrayList<String>();
            for (int i = 0; i < tagJson.length() && i < 8; i++) {
                tags.add(tagJson.getString(i));
            }
            description.setTags(tags);
            JSONObject captionJson = descriptionJson.getJSONArray("captions").getJSONObject(0);
            description.setDescription(captionJson.getString("text"));
            description.setDesConfidence(captionJson.getDouble("confidence"));
//            JSONArray faceJson = result.getJSONArray("faces");
//            List<Face> faces = new ArrayList<Face>();
//            for (int i = 0; i < faceJson.length(); i++) {
//                JSONObject faceObj = faceJson.getJSONObject(i);
//                JSONObject rec = faceObj.getJSONObject("faceRectangle");
//                faces.add(new Face(faceObj.getInt("age"),faceObj.getString("gender"),rec.getInt("width"),
//                        rec.getInt("height"),rec.getInt("left"),rec.getInt("top")));
//            }
//            description.setFaces(faces);
//            description.setFace(!(faces.size()==0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return description;
    }

}
