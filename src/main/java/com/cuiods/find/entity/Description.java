package com.cuiods.find.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by cuihao on 2016/8/17.
 */
public class Description {

    private String description;
    private double desConfidence;
    private List<String> tags;
    private boolean isFace;
    private List<Face> faces;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDesConfidence() {
        return desConfidence;
    }

    public void setDesConfidence(double desConfidence) {
        this.desConfidence = desConfidence;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isFace() {
        return isFace;
    }

    public void setFace(boolean face) {
        isFace = face;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "Description{" +
                "description='" + description + '\'' +
                ", desConfidence=" + desConfidence +
                ", tags=" + tags +
                ", isFace=" + isFace +
                ", faces=" + faces +
                '}';
    }
}
