package com.santahat.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * manage api token
 */
@Component
public class APIManager {

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
     * api url
     */
    @Value("#{configProperties['api.detect']}")
    private String api_face_detect;

    @Value("#{configProperties['url.preffix']}")
    private String url_preffix;

    public String getFaceToken(){
        String[] tokens = {face_token1_1,face_token1_2,face_token2_1,face_token2_2,face_token3_1,face_token3_2,face_token4_1,
                face_token4_2,face_token5_1,face_token5_2};
        int index = (int)(Math.random()*tokens.length);
        return tokens[index];
    }


    public String getPreffix(){
        return url_preffix;
    }

    public String getFaceDetectApi() {
        return api_face_detect;
    }

}
