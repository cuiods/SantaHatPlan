package com.cuiods.find.model;

import com.cuiods.find.entity.Face;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.pattern.LineSeparatorPatternConverter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * face api model
 * @author cuihao
 */
@Component
public class FindFace {

    @Autowired
    private APIManager apiManager;

    public void createGroup() {
        HttpClient httpclient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(apiManager.getPersonGroupApi());
            URI uri = builder.build();
            HttpPut request = new HttpPut(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", apiManager.getFaceToken());
            // Request body
            JSONObject object = new JSONObject();
            object.put("name","find");
            object.put("userData","faces of user to be recognized");
            StringEntity reqEntity = new StringEntity(object.toString());
            request.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createPerson() {
        HttpClient httpclient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(apiManager.getPersonGroupApi()+"/persons");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", apiManager.getFaceToken());
            // Request body
            JSONObject object = new JSONObject();
            object.put("name","yyy");
            object.put("userData","faces of yyy");
            StringEntity reqEntity = new StringEntity(object.toString());
            request.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addFace(String url) {
        HttpClient httpclient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(apiManager.getPersonGroupApi()+"/persons/"+apiManager.token1_person+"/persistedFaces");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", apiManager.getFaceToken());
            // Request body
            JSONObject object = new JSONObject();
            object.put("url",apiManager.getPreffix()+url);
            StringEntity reqEntity = new StringEntity(object.toString());
            request.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printPerson(){
        HttpClient httpclient = HttpClients.createDefault();

        try {
            com.mashape.unirest.http.HttpResponse<JsonNode> jsonResponse = Unirest.get(apiManager.getPersonGroupApi()+"/persons/"+apiManager.token1_person)
                    .header("Content-Type", "application/json")
                    .header("Ocp-Apim-Subscription-Key", apiManager.getFaceToken())
                    .asJson();
            System.out.println(jsonResponse.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void traning() {
        HttpClient httpclient = HttpClients.createDefault();

        try {
            URIBuilder builder = new URIBuilder(apiManager.getPersonGroupApi()+"/train");
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Ocp-Apim-Subscription-Key", apiManager.getFaceToken());
            // Request body
//            StringEntity reqEntity = new StringEntity("{body}");
//            request.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getStatus() {
        try {
            com.mashape.unirest.http.HttpResponse<JsonNode> jsonResponse = Unirest.get(apiManager.getPersonGroupApi()+"/training")
                    .header("Content-Type", "application/json")
                    .header("Ocp-Apim-Subscription-Key", apiManager.getFaceToken())
                    .asJson();
            System.out.println(jsonResponse.getBody());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Face> detectFaces(String url) {
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
                String faceId = object.getString("faceId");
                JSONObject rec = object.getJSONObject("faceRectangle");
                Face face = new Face(faceId,rec.getInt("width"),rec.getInt("height"),rec.getInt("left"),rec.getInt("top"));
                faces.add(face);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return faces;
    }

    public Face identifyFaces(List<Face> faces) {
        if (faces.size()==0) {
            return new Face("",0,0,0,0);
        }
        System.out.println(faces);
        List<String> faceIds = new ArrayList<String>(faces.size());
        for (Face face: faces) {
            faceIds.add(face.getFaceId());
        }
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("faceIds",faceIds);
            jsonObject.put("personGroupId","cuiods_find");
            jsonObject.put("maxNumOfCandidatesReturned",1);
            com.mashape.unirest.http.HttpResponse<JsonNode> jsonResponse = Unirest.post(apiManager.getFaceIdentifyApi())
                    .header("Content-Type", "application/json")
                    .header("Ocp-Apim-Subscription-Key", apiManager.getFaceToken())
                    .body(jsonObject)
                    .asJson();
            System.out.println(jsonResponse.getBody());
            JSONArray jsonArray = jsonResponse.getBody().getArray();
            String resultId = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray array = jsonArray.getJSONObject(i).getJSONArray("candidates");
                if (array.length()!=0 && array.getJSONObject(0).getDouble("confidence")>0.5){
                    resultId = jsonArray.getJSONObject(i).getString("faceId");
                    System.out.println("===result==="+resultId);
                }
            }
            if (resultId!="") {
                for (Face face: faces) {
                    if (face.getFaceId().equals(resultId)) {
                        return face;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new Face("",0,0,0,0);
    }


}
