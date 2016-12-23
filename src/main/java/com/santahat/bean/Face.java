package com.santahat.bean;

import lombok.Data;

/**
 * face location
 */
@Data
public class Face {
    private int width;
    private int height;
    private int left;
    private int top;
    public Face(int width, int height, int left, int top) {
        this.width = width;
        this.height = height;
        this.left = left;
        this.top = top;
    }
}
