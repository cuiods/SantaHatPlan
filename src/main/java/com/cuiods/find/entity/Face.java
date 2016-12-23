package com.cuiods.find.entity;

/**
 * Created by cuihao on 2016/8/17.
 */
public class Face {
    private String faceId;
    private String description;
    private int width;
    private int height;
    private int left;
    private int top;

    public Face(String faceId, int width, int height, int left, int top) {
        this.faceId = faceId;
        this.width = width;
        this.height = height;
        this.left = left;
        this.top = top;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "Face{" +
                "faceId='" + faceId + '\'' +
                ", description='" + description + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", left=" + left +
                ", top=" + top +
                '}';
    }
}
