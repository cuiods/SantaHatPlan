package com.santahat.bean;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * Hat vo
 */
@Data
public class Hat {

    //face location
    private int width;
    private int height;
    private int left;
    private int top;

    //hat image url
    private String hat_url;

    public Hat(Face face) {
        BeanUtils.copyProperties(face,this,"id");
    }

}
