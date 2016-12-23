package com.cuiods.find.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * provide api token and api url
 * @author cuihao
 */
@Component
public class APIManager {

    /**
     * description api token
     */
    @Value("#{configProperties['description.token1.1']}")
    private String des_token1_1;
    @Value("#{configProperties['description.token1.2']}")
    private String des_token1_2;
    @Value("#{configProperties['description.token2.1']}")
    private String des_token2_1;
    @Value("#{configProperties['description.token2.2']}")
    private String des_token2_2;
    @Value("#{configProperties['description.token3.1']}")
    private String des_token3_1;
    @Value("#{configProperties['description.token3.2']}")
    private String des_token3_2;
    @Value("#{configProperties['description.token4.1']}")
    private String des_token4_1;
    @Value("#{configProperties['description.token4.2']}")
    private String des_token4_2;
    @Value("#{configProperties['description.token5.1']}")
    private String des_token5_1;
    @Value("#{configProperties['description.token5.2']}")
    private String des_token5_2;

    /**
     * face api token
     */
    @Value("#{configProperties['face.token1.1']}")
    private String face_token1_1;
    @Value("#{configProperties['face.token1.2']}")
    private String face_token1_2;
    @Value("#{configProperties['face.token2.1']}")
    private String face_token2_1;
    @Value("#{configProperties['face.token2.2']}")
    private String face_token2_2;
    @Value("#{configProperties['face.token3.1']}")
    private String face_token3_1;
    @Value("#{configProperties['face.token3.2']}")
    private String face_token3_2;
    @Value("#{configProperties['face.token4.1']}")
    private String face_token4_1;
    @Value("#{configProperties['face.token4.2']}")
    private String face_token4_2;
    @Value("#{configProperties['face.token5.1']}")
    private String face_token5_1;
    @Value("#{configProperties['face.token5.2']}")
    private String face_token5_2;


    /**
     * person url
     */
    @Value("#{configProperties['token1.person']}")
    public String token1_person;

    /**
     * api url
     */
    @Value("#{configProperties['api.describe']}")
    private String api_describe;
    @Value("#{configProperties['api.persongroup']}")
    private String api_person_group;
    @Value("#{configProperties['api.detect']}")
    private String api_face_detect;
    @Value("#{configProperties['api.identify']}")
    private String api_face_identify;

    @Value("#{configProperties['url.preffix']}")
    private String url_preffix;

    public String getDesToken(){
        String[] tokens = {des_token1_1,des_token1_2,des_token2_1,des_token2_2,des_token3_1,des_token3_2,des_token4_1,
                des_token4_2,des_token5_1,des_token5_2};
        int index = (int)(Math.random()*tokens.length);
        return tokens[index];
    }

    public String getFaceToken(){
//        String[] tokens = {face_token1_1,face_token1_2,face_token2_1,face_token2_2,face_token3_1,face_token3_2,face_token4_1,
//                face_token4_2,face_token5_1,face_token5_2};
//        int index = (int)(Math.random()*tokens.length);
        return face_token2_1;
    }

    public String getDescriptionApi(){
        return api_describe;
    }

    public String getPreffix(){
        return url_preffix;
    }

    public String getFaceDetectApi() {
        return api_face_detect;
    }

    public String getFaceIdentifyApi() {
        return api_face_identify;
    }

    public String getPersonGroupApi() { return api_person_group; }
}
